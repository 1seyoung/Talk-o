package com.talko.websocket.exception.error;

import com.talko.websocket.exception.ErrorCode;
import com.talko.websocket.exception.WebSocketException;

public class UnauthorizedException extends WebSocketException {
  public UnauthorizedException() {
    super(ErrorCode.UNAUTHORIZED);
  }

  public UnauthorizedException(String message) {
    super(ErrorCode.UNAUTHORIZED, message);
  }
}
