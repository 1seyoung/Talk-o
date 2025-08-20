package com.talko.web.exception.business;

import com.talko.web.exception.BusinessException;
import com.talko.web.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {
  public UserNotFoundException() {
    super(ErrorCode.USER_NOT_FOUND);
  }

  public UserNotFoundException(String message) {
    super(ErrorCode.USER_NOT_FOUND, message);
  }
}
