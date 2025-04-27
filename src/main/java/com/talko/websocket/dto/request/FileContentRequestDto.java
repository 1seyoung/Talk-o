package com.talko.websocket.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileContentRequestDto {
  private String fileName;
  private String fileUrl;
  private Long fileSize;
}
