package com.talko.exception.business;

import com.talko.exception.BusinessException;
import com.talko.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {
  public UserNotFoundException() {
    super(ErrorCode.USER_NOT_FOUND);
  }

  public UserNotFoundException(String message) {
    super(ErrorCode.USER_NOT_FOUND, message);
  }
}
