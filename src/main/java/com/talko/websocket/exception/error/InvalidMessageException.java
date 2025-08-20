package com.talko.websocket.exception.error;

import com.talko.websocket.exception.ErrorCode;
import com.talko.websocket.exception.WebSocketException;

public class InvalidMessageException extends WebSocketException {
  public InvalidMessageException() {
    super(ErrorCode.INVALID_MESSAGE);
  }

  public InvalidMessageException(String message) {
    super(ErrorCode.INVALID_MESSAGE, message);
  }

}
