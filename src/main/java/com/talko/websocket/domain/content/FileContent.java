package com.talko.websocket.domain.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
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
