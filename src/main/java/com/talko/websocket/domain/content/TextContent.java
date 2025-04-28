package com.talko.websocket.domain.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TextContent implements MessageContent{
  private final String text;

  @Override
  public ContentType getContentType() {
    return ContentType.TEXT;
  }
}
