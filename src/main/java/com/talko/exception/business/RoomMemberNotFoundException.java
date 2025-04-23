package com.talko.exception.business;

import com.talko.exception.BusinessException;
import com.talko.exception.ErrorCode;

public class RoomMemberNotFoundException extends BusinessException {

  public RoomMemberNotFoundException() {
    super(ErrorCode.ROOM_MEMBER_NOT_FOUND);
  }

  public RoomMemberNotFoundException(String message) {
    super(ErrorCode.ROOM_MEMBER_NOT_FOUND, message);
  }
}
