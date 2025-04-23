package com.talko.service;

import com.talko.domain.Invitation;
import com.talko.domain.Room;
import com.talko.domain.type.AuthInfo;
import com.talko.domain.type.RoomRole;
import com.talko.dto.response.InvitationResponseDto;
import com.talko.exception.business.RoomNotFoundException;
import com.talko.mapper.InvitationMapper;
import com.talko.mapper.RoomMapper;
import com.talko.mapper.RoomMemberMapper;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvitationService {

  private final InvitationMapper invitationMapper;
  private final RoomMapper roomMapper;
  private final RoomMemberMapper roomMemberMapper;

  public InvitationService(InvitationMapper invitationMapper, RoomMapper roomMapper,
      RoomMemberMapper roomMemberMapper) {
    this.invitationMapper = invitationMapper;
    this.roomMapper = roomMapper;
    this.roomMemberMapper = roomMemberMapper;
  }

  public List<InvitationResponseDto> getMyInvitations(AuthInfo authInfo) {

    List<Invitation> invitations = invitationMapper.findAllByInviteeId(authInfo.getUserId());
    return invitations.stream()
        .map(InvitationResponseDto::from)
        .toList();
  }

  public InvitationResponseDto getInvitationDetail(Long inviteId, AuthInfo authInfo) {
    Invitation invitation = invitationMapper.findById(inviteId);

    if (invitation == null) {
      throw new IllegalArgumentException("Invitation not found or you are not the invitee");
      //TODO : custom exception
    }

    if (!invitation.getInviteeId().equals(authInfo.getUserId())) {
      throw new IllegalArgumentException("You are not the invitee of this invitation");
      //TODO : custom exception
    }
      return InvitationResponseDto.from(invitation);

  }

  @Transactional
  public void acceptInvitation(Long inviteId, AuthInfo authInfo){
      Invitation invitation = invitationMapper.findById(inviteId);
      if(!invitation.isInvitee(authInfo.getUserId())) {
        throw new IllegalArgumentException("You are not the invitee of this invitation");
        //TODO : custom exception
      }
      invitation.accept();
      invitationMapper.update(invitation);

      Room room = roomMapper.findById(invitation.getChatroomId());
      if (room == null) {
        throw new RoomNotFoundException("Room not found");
      }
    roomMemberMapper.insertRoomMember(room.getId(), authInfo.getUserId(), RoomRole.MEMBER.name());
    }


  public void rejectInvitation(Long inviteId, AuthInfo authInfo) {
    Invitation invitation = invitationMapper.findById(inviteId);
    if(!invitation.isInvitee(authInfo.getUserId())) {
      throw new IllegalArgumentException("You are not the invitee of this invitation");
      //TODO : custom exception
    }
    invitation.reject();
    invitationMapper.update(invitation);
  }
}
