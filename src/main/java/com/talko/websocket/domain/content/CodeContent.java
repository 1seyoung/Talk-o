package com.talko.websocket.domain.content;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CodeContent implements MessageContent{
  private final SourceCodeLanguage language;
  private final String sourceCode;


  @Override
  public ContentType getContentType() {
    return ContentType.CODE;
  }
}
