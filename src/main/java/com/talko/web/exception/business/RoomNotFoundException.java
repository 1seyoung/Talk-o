package com.talko.web.exception.business;

import com.talko.web.exception.BusinessException;
import com.talko.web.exception.ErrorCode;

public class RoomNotFoundException extends BusinessException {
  public RoomNotFoundException() {
    super(ErrorCode.ROOM_NOT_FOUND);
  }

  public RoomNotFoundException(String message) {
    super(ErrorCode.ROOM_NOT_FOUND, message);
  }
}
