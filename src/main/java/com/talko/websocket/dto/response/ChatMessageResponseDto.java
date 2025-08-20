package com.talko.websocket.dto.response;

// ChatMessageResponseDto.java
import com.talko.domain.type.AuthInfo;
import com.talko.websocket.domain.ChatMessage;
import com.talko.websocket.domain.content.CodeContent;
import com.talko.websocket.domain.content.FileContent;
import com.talko.websocket.domain.content.ImageContent;
import com.talko.websocket.domain.content.TextContent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


// TODO : 리팩토링 필요 (instanceof 사용을 피하고, ContentType에 따라 다르게 처리하는 방법으로 개선)

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageResponseDto {
  private String messageType;
  private String contentType;
  private Object content;
  private Long senderId;
  private String senderName;
  private Date timestamp;

  public static ChatMessageResponseDto from(ChatMessage message, AuthInfo sender) {
    ChatMessageResponseDto dto = new ChatMessageResponseDto();
    dto.setMessageType(message.getMessageType().name());
    dto.setContentType(message.getContentType().name());

    // 콘텐츠 타입에 따라 적절한 형태로 변환
    if (message.getContent() instanceof TextContent) {
      TextContent textContent = (TextContent) message.getContent();
      Map<String, String> contentMap = new HashMap<>();
      contentMap.put("text", textContent.getText());
      dto.setContent(contentMap);

    } else if (message.getContent() instanceof CodeContent) {
      CodeContent codeContent = (CodeContent) message.getContent();
      Map<String, String> contentMap = new HashMap<>();
      contentMap.put("code", codeContent.getSourceCode());
      contentMap.put("language", codeContent.getLanguage().name());
      dto.setContent(contentMap);
    } else if (message.getContent() instanceof ImageContent) {
      ImageContent imageContent = (ImageContent) message.getContent();
      Map<String, String> contentMap = new HashMap<>();
      contentMap.put("url", imageContent.getImageUrl());
      dto.setContent(contentMap);
    } else if (message.getContent() instanceof FileContent) {
      FileContent fileContent = (FileContent) message.getContent();
      Map<String, Object> contentMap = new HashMap<>();
      contentMap.put("fileName", fileContent.getFileName());
      contentMap.put("fileUrl", fileContent.getFileUrl());
      contentMap.put("fileSize", fileContent.getFileSize());
      contentMap.put("mimeType", "application/octet-stream"); // 기본값, 필요시 수정
      dto.setContent(contentMap);
    }

    dto.setSenderId(sender.getUserId());
    dto.setSenderName(sender.getName());
    dto.setTimestamp(new Date());

    return dto;
  }
}