package com.talko.websocket.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WebSocketErrorResponse {
  private final LocalDateTime timestamp;
  private final String code;
  private final String message;
  private final String destination; // WebSocket은 path 대신 destination (예: /app/chat.send)

  private final List<FieldError> errors;

  public WebSocketErrorResponse(String code, String message, String destination) {
    this(code, message, destination, new ArrayList<>());
  }

  public WebSocketErrorResponse(String code, String message, String destination, List<FieldError> errors) {
    this(LocalDateTime.now(), code, message, destination, errors);
  }

  @Getter
  @ToString
  @AllArgsConstructor
  public static class FieldError {
    private final String field;
    private final Object value;
    private final String reason;
  }
}