package com.talko.websocket.handler;

import com.talko.common.annotation.Auth;
import com.talko.domain.type.AuthInfo;
import com.talko.websocket.dto.request.ChatMessageRequestDto;
import com.talko.websocket.service.ChatMessageService;
import com.talko.websocket.utils.WebSocketAuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatMessageHandler {

  private final ChatMessageService chatMessageService;

  /**
  일반 텍스트 메시지 전송
  클라이언트에서 /app/chat/message/{roomId}로 메시지를 전송하면 처리하는 핸들러(컨트롤러)
   */
  @MessageMapping("/chat/message/{roomId}")
  public void sendMessage(@DestinationVariable Long roomId, ChatMessageRequestDto request , SimpMessageHeaderAccessor headerAccessor) {
    AuthInfo authInfo = WebSocketAuthUtils.getAuthInfo(headerAccessor);
    chatMessageService.sendMessage(roomId, request, authInfo);
  }

  /**
   * WEBSOCKET TODO 1 : 이미지 메시지  전송
   * WEBSOCKET TODO 2 : 파일 메시지 전송
   * WEBSOCKET TODO 3 : 읽음 처리
   * ?? 가 필요한가 -> sendMessage가 못하나
   */
}
