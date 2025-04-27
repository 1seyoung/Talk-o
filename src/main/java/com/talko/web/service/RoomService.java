package com.talko.web.service;

import com.talko.domain.Invitation;
import com.talko.domain.Room;
import com.talko.domain.RoomMember;
import com.talko.domain.User;
import com.talko.domain.type.RoomRole;
import com.talko.web.dto.request.InviteRequestDto;
import com.talko.web.dto.request.RoomCreateRequestDto;
import com.talko.web.dto.response.InviteResponseDto;
import com.talko.web.dto.response.MemberInfoDto;
import com.talko.web.dto.response.RoomCreateResponseDto;
import com.talko.domain.type.AuthInfo;
import com.talko.web.dto.response.RoomInfoResponseDto;
import com.talko.web.dto.response.RoomSimpleResponseDto;
import com.talko.web.dto.response.UserNameDto;
import com.talko.web.exception.business.RoomMemberNotFoundException;
import com.talko.web.exception.business.RoomNotFoundException;
import com.talko.web.exception.business.RoomNotHostException;
import com.talko.web.exception.business.UserNotFoundException;
import com.talko.web.mapper.InvitationMapper;
import com.talko.web.mapper.RoomMapper;
import com.talko.web.mapper.RoomMemberMapper;
import com.talko.web.mapper.UserMapper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomService {

  private final UserMapper userMapper;
  private final RoomMapper roomMapper;
  private final InvitationMapper invitationMapper;
  private final RoomMemberMapper roomMemberMapper;

  public RoomService(UserMapper userMapper, RoomMapper roomMapper,
      InvitationMapper invitationMapper, RoomMemberMapper roomMemberMapper) {
    this.userMapper = userMapper;
    this.roomMapper = roomMapper;
    this.invitationMapper = invitationMapper;
    this.roomMemberMapper = roomMemberMapper;
  }

  @Transactional
  public RoomCreateResponseDto createRoom(RoomCreateRequestDto request, AuthInfo authInfo) {

    User host = userMapper.findById(authInfo.getUserId());

    if (host == null) {
      throw new UserNotFoundException("Host not found");
    }

    Room newRoom = Room.of(request.getName(), host);
    roomMapper.insertRoom(newRoom);

    roomMemberMapper.insertRoomMember(
        newRoom.getId(),
        host.getId(),
        RoomRole.HOST.name()
    );

    return RoomCreateResponseDto.from(newRoom);
  }

  public InviteResponseDto inviteUserToRoom(Long roomId, InviteRequestDto request, AuthInfo authInfo) {
    // TODO : 초대하기 기능 구현 : 까먹고 id 로 초대됨..다시 수정하기
    User host = userMapper.findById(authInfo.getUserId());
    if (host == null) {
      throw new UserNotFoundException("Host not found");
    }

    Room room = roomMapper.findById(roomId);
    if (room == null) {
      throw new RoomNotFoundException("Room not found");
    }

    if (!room.isHost(host.getId())) {
      throw new RoomNotHostException("User is not the host of the room");
    }

    User invitee = userMapper.findById(request.getInviteeId());

    if (invitee == null) {
      throw new UserNotFoundException("Invitee not found");
    }

    Invitation invitation = Invitation.of(room.getId(),host.getId(), invitee.getId());
    invitationMapper.insertInvitation(invitation);

    return InviteResponseDto.from(invitation);
  }

  public List<RoomSimpleResponseDto> getRoomsByUser(AuthInfo authInfo) {
    List<Long> roomIds = roomMemberMapper.findRoomIdsByUserId(authInfo.getUserId());
    if (roomIds.isEmpty()) {return List.of();}

    List<Room> rooms = roomMapper.findByIds(roomIds);

    return rooms.stream()
        .map(RoomSimpleResponseDto::from)
        .toList();

  }

  public RoomInfoResponseDto getRoomInfo(Long roomId, AuthInfo authInfo) {
    validateRoomAccess(roomId, authInfo.getUserId());

    Room room = roomMapper.findById(roomId);
    if (room == null) {
      throw new RoomNotFoundException("Room not found");
    }

    List<MemberInfoDto> members = getMemberInfoList(roomId);
    String hostName = extractHostName(members);


    return RoomInfoResponseDto.from(room, members, hostName);

  }

  private List<MemberInfoDto> getMemberInfoList(Long roomId) {
    List<RoomMember> roomMembers = roomMemberMapper.findByRoomId(roomId);

    List<Long> userIds = roomMembers.stream()
        .map(RoomMember::getUserId)
        .distinct()
        .toList();

    Map<Long, UserNameDto> userNameMap = userMapper.findNamesByIds(userIds);

    List<MemberInfoDto> members = roomMembers.stream()
        .map(rm -> {
          UserNameDto dto = userNameMap.get(rm.getUserId());
          return MemberInfoDto.of(
              rm.getUserId(),
              dto != null ? dto.getName() : "알 수 없음",
              rm.getRole()
          );
        })
        .toList();

    return members;
  }

  private String extractHostName(List<MemberInfoDto> members) {
    return members.stream()
        .filter(m -> m.getRole() == RoomRole.HOST)
        .findFirst()
        .map(MemberInfoDto::getName)
        .orElse("알 수 없음");
  }

  private void validateRoomAccess(Long roomId, Long userId) {
    boolean isMember = roomMemberMapper.existsByRoomIdAndUserId(roomId, userId);
    if (!isMember) {
      throw new RoomMemberNotFoundException("채팅방에 참여하지 않은 사용자입니다.");
    }
  }
}
