package com.talko.web.exception.business;


import com.talko.web.exception.BusinessException;
import com.talko.web.exception.ErrorCode;

public class RoomNotHostException extends BusinessException {

  public RoomNotHostException() {
    super(ErrorCode.ROOM_NOT_HOST);
  }

  public RoomNotHostException(String message) {
    super(ErrorCode.ROOM_NOT_HOST, message);
  }
}
