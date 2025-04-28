package com.talko.web.controller;

import com.talko.common.annotation.Auth;
import com.talko.web.dto.request.InviteRequestDto;
import com.talko.web.dto.request.RoomCreateRequestDto;
import com.talko.web.dto.response.InviteResponseDto;
import com.talko.web.dto.response.RoomCreateResponseDto;
import com.talko.domain.type.AuthInfo;
import com.talko.web.dto.response.RoomInfoResponseDto;
import com.talko.web.dto.response.RoomSimpleResponseDto;
import com.talko.web.service.RoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {

  private final RoomService roomService;

  @PostMapping
  public ResponseEntity<RoomCreateResponseDto> createRoom(@RequestBody RoomCreateRequestDto request ,@Auth AuthInfo authInfo){
    RoomCreateResponseDto response = roomService.createRoom(request, authInfo);
    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<List<RoomSimpleResponseDto>> getMyChatRooms(@Auth AuthInfo authInfo) {
    List<RoomSimpleResponseDto> rooms = roomService.getRoomsByUser(authInfo);
    return ResponseEntity.ok(rooms);
  }

  @GetMapping("/{roomId}")
  public ResponseEntity<RoomInfoResponseDto> getRoomInfo(@PathVariable Long roomId, @Auth AuthInfo authInfo) {
    RoomInfoResponseDto response = roomService.getRoomInfo(roomId, authInfo);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/self")
  public ResponseEntity<RoomInfoResponseDto> getSelfChatRoom(@Auth AuthInfo authInfo) {
    RoomInfoResponseDto response = roomService.getSelfRoomInfo(authInfo);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/{roomId}/invite")
  public ResponseEntity<InviteResponseDto> inviteUserToRoom(@PathVariable Long roomId, @RequestBody InviteRequestDto request, @Auth AuthInfo authInfo) {
    InviteResponseDto response = roomService.inviteUserToRoom(roomId, request, authInfo);
    return ResponseEntity.ok(response);
  }

}
