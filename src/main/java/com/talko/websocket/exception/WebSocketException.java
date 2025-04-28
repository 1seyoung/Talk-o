package com.talko.websocket.exception;

import lombok.Getter;

@Getter
public class WebSocketException extends RuntimeException {
  private final ErrorCode errorCode;

  public WebSocketException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public WebSocketException(ErrorCode errorCode, String customMessage) {
    super(customMessage);
    this.errorCode = errorCode;
  }
}