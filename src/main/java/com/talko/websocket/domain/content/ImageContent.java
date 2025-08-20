package com.talko.websocket.domain.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ImageContent implements MessageContent{
  private final String imageUrl;

  @Override
  public ContentType getContentType() {
    return ContentType.IMAGE;
  }
}
