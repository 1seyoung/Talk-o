package com.talko.websocket.handler;

import com.talko.common.annotation.Auth;
import com.talko.common.annotation.WsAuth;
import com.talko.domain.type.AuthInfo;
import com.talko.websocket.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller
@RequiredArgsConstructor
public class ChatRoomHandler {

  private final ChatRoomService chatRoomService;

  @MessageMapping("/chat/enter/{roomId}")
  public void enterRoom(@DestinationVariable Long roomId, SimpMessageHeaderAccessor headerAccessor, @WsAuth AuthInfo authInfo) {
    chatRoomService.enterRoom(roomId, headerAccessor.getSessionId(), authInfo.getUserId());
  }

  @EventListener
  public void handleSessionDisconnect(SessionDisconnectEvent event) {
    String sessionId = event.getSessionId();
    chatRoomService.leaveRoom(sessionId);
  }
}
