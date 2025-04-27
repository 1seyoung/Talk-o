package com.talko.websocket.exception.error;

import com.talko.websocket.exception.ErrorCode;
import com.talko.websocket.exception.WebSocketException;

public class RoomNotFoundException extends WebSocketException {
  public RoomNotFoundException() {
    super(ErrorCode.ROOM_NOT_FOUND);
  }

  public RoomNotFoundException(String message) {
    super(ErrorCode.ROOM_NOT_FOUND, message);
  }
}
