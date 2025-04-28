package com.talko.websocket.domain.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CodeContent implements MessageContent{
  private final SourceCodeLanguage language;
  private final String sourceCode;


  @Override
  public ContentType getContentType() {
    return ContentType.CODE;
  }
}
