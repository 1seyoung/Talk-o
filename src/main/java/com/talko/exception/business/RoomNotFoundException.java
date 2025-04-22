package com.talko.exception.business;

import com.talko.exception.BusinessException;
import com.talko.exception.ErrorCode;

public class RoomNotFoundException extends BusinessException {
  public RoomNotFoundException() {
    super(ErrorCode.ROOM_NOT_FOUND);
  }

  public RoomNotFoundException(String message) {
    super(ErrorCode.ROOM_NOT_FOUND, message);
  }
}
