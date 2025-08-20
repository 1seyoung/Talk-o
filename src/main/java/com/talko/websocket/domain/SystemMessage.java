package com.talko.websocket.domain;

import com.talko.websocket.domain.content.ContentType;
import com.talko.websocket.domain.content.MessageContent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class SystemMessage implements ChatMessage{
  private final MessageContent content;

  @Override
  public MessageType getMessageType() {
    return MessageType.SYSTEM;
  }

  @Override
  public ContentType getContentType() {
    return content.getContentType();
  }

  @Override
  public MessageContent getContent() {
    return content;
  }
}
