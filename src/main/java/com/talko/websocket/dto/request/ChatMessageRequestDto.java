package com.talko.websocket.dto.request;

import com.talko.websocket.domain.MessageType;
import com.talko.websocket.domain.content.ContentType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessageRequestDto {
  private MessageType messageType;
  private ContentType contentType;
  private Object content;
}
