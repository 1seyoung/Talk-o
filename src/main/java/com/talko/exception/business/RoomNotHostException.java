package com.talko.exception.business;


import com.talko.exception.BusinessException;
import com.talko.exception.ErrorCode;

public class RoomNotHostException extends BusinessException {

  public RoomNotHostException() {
    super(ErrorCode.ROOM_NOT_HOST);
  }

  public RoomNotHostException(String message) {
    super(ErrorCode.ROOM_NOT_HOST, message);
  }
}
