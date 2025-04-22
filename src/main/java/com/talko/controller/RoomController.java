package com.talko.controller;

import com.talko.common.annotation.Auth;
import com.talko.dto.request.InviteRequestDto;
import com.talko.dto.request.RoomCreateRequestDto;
import com.talko.dto.response.InviteResponseDto;
import com.talko.dto.response.RoomCreateResponseDto;
import com.talko.domain.type.AuthInfo;
import com.talko.dto.response.RoomInfoResponseDto;
import com.talko.dto.response.RoomSimpleResponseDto;
import com.talko.service.RoomService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

  private final RoomService roomService;

  public RoomController(RoomService roomService) {
    this.roomService = roomService;
  }

  //  채팅방 생성             POST   /api/rooms
  @PostMapping
  public ResponseEntity<RoomCreateResponseDto> createRoom(@RequestBody RoomCreateRequestDto request ,@Auth AuthInfo authInfo){
    RoomCreateResponseDto response = roomService.createRoom(request, authInfo);
    return ResponseEntity.ok(response);
  }

//  채팅방 목록 조회(내가 참여하고있는 전체목록)        GET    /api/rooms
  @GetMapping
  public ResponseEntity<List<RoomSimpleResponseDto>> getMyChatRooms(@Auth AuthInfo authInfo) {
    List<RoomSimpleResponseDto> rooms = roomService.getRoomsByUser(authInfo);
    return ResponseEntity.ok(rooms);
  }

//  특정 채팅방 정보 조회 (선택한거 하나)  GET    /api/rooms/{roomId}
  @GetMapping("/{roomId}")
  public ResponseEntity<RoomInfoResponseDto> getRoomInfo(@PathVariable Long roomId, @Auth AuthInfo authInfo) {
    RoomInfoResponseDto response = roomService.getRoomInfo(roomId, authInfo);
    return ResponseEntity.ok(response);
  }

  //채팅방 초대             POST   /api/rooms/{roomId}/invite
  @PostMapping("/{roomId}/invite")
  public ResponseEntity<InviteResponseDto> inviteUserToRoom(@PathVariable Long roomId, @RequestBody InviteRequestDto request, @Auth AuthInfo authInfo) {
    InviteResponseDto response = roomService.inviteUserToRoom(roomId, request, authInfo);
    return ResponseEntity.ok(response);
  }
}
