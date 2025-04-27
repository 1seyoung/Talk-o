package com.talko.websocket.domain.content;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImageContent implements MessageContent{
  private final String imageUrl;

  @Override
  public ContentType getContentType() {
    return ContentType.IMAGE;
  }
}
