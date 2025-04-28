package com.talko.websocket.dto.request;

import com.talko.websocket.domain.content.SourceCodeLanguage;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CodeContentRequestDto {
  private SourceCodeLanguage language;
  private String sourceCode;
}
