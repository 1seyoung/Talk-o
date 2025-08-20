package com.talko.websocket.domain;

import com.talko.websocket.domain.content.ContentType;
import com.talko.websocket.domain.content.MessageContent;

public interface ChatMessage {
  MessageType getMessageType();
  ContentType getContentType();
  MessageContent getContent();
}
