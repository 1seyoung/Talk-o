package com.talko.websocket.service;

import com.talko.domain.type.AuthInfo;
import com.talko.websocket.domain.ChatMessage;
import com.talko.websocket.dto.request.ChatMessageRequestDto;
import com.talko.websocket.dto.response.ChatMessageResponseDto;
import com.talko.websocket.service.factory.ChatMessageFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatMessageService {

  private final SimpMessageSendingOperations simpMessageSendingOperations;
  private final ChatMessageFactory chatMessageFactory;

  public void sendMessage(Long roomId, ChatMessageRequestDto request, AuthInfo authInfo) {
    ChatMessage message = chatMessageFactory.create(request);
    String destination = buildDestination(roomId, message, authInfo.getUserId());


    ChatMessageResponseDto responseDto = ChatMessageResponseDto.from(message, authInfo);
    simpMessageSendingOperations.convertAndSend(destination, responseDto);
    log.info("메시지 전송 완료 - destination={}, messageType={}, contentType={}",
        destination, message.getMessageType(), message.getContentType());
  }

  private String buildDestination(Long roomId, ChatMessage message, Long userId) {
    return switch (message.getMessageType()) {
      case GROUP, BOT -> "/topic/chat/room/" + roomId;
      case PRIVATE, SYSTEM -> "/user/"+userId+"/queue/chat";
    };
  }
}
