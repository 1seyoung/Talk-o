package com.talko.web.exception.business;

import com.talko.web.exception.BusinessException;
import com.talko.web.exception.ErrorCode;

public class InvalidCredentialsException extends BusinessException {
  public InvalidCredentialsException() {
    super(ErrorCode.INVALID_CREDENTIALS);
  }

  public InvalidCredentialsException(String message) {
    super(ErrorCode.INVALID_CREDENTIALS, message);
  }
}
