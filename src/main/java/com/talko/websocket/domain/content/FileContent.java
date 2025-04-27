package com.talko.websocket.domain.content;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileContent implements MessageContent{
  private final String fileName;
  private final String fileUrl;
  private final long fileSize;

  @Override
  public ContentType getContentType() {
    return ContentType.FILE;
  }
}
