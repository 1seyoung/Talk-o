package com.talko.websocket.service.factory;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.talko.websocket.domain.BotMessage;
import com.talko.websocket.domain.ChatMessage;
import com.talko.websocket.domain.GroupMessage;
import com.talko.websocket.domain.PrivateMessage;
import com.talko.websocket.domain.SystemMessage;
import com.talko.websocket.domain.content.CodeContent;
import com.talko.websocket.domain.content.ContentType;
import com.talko.websocket.domain.content.FileContent;
import com.talko.websocket.domain.content.ImageContent;
import com.talko.websocket.domain.content.MessageContent;
import com.talko.websocket.domain.content.TextContent;
import com.talko.websocket.dto.request.ChatMessageRequestDto;
import com.talko.websocket.dto.request.CodeContentRequestDto;
import com.talko.websocket.dto.request.FileContentRequestDto;
import com.talko.websocket.dto.request.ImageContentRequestDto;
import com.talko.websocket.dto.request.TextContentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatMessageFactory {

  private final ObjectMapper objectMapper;

  public ChatMessage create(ChatMessageRequestDto request) {
    MessageContent content = createContent(request.getContentType(), request.getContent());
    return switch (request.getMessageType()) {
      case GROUP -> new GroupMessage(content);
      case PRIVATE -> new PrivateMessage(content);
      case SYSTEM -> new SystemMessage(content);
      case BOT -> new BotMessage(content);
    };
  }

  // TODO : ObjectMapper , 직렬화, 역직렬화 공부
  private MessageContent createContent(ContentType contentType, Object content) {
    return switch (contentType) {
      case FILE -> {
        FileContentRequestDto file = objectMapper.convertValue(content, FileContentRequestDto.class);
        yield new FileContent(file.getFileName(), file.getFileUrl(), file.getFileSize());
      }
      case CODE -> {
        CodeContentRequestDto code = objectMapper.convertValue(content, CodeContentRequestDto.class);
        yield new CodeContent(code.getLanguage(), code.getSourceCode());
      }
      case IMAGE -> {
        ImageContentRequestDto image = objectMapper.convertValue(content, ImageContentRequestDto.class);
        yield new ImageContent(image.getImageUrl());
      }
      case TEXT -> {
        TextContentRequestDto text = objectMapper.convertValue(content, TextContentRequestDto.class);
        yield new TextContent(text.getText());
      }
    };
  }

}
