package com.talko.exception.business;

import com.talko.exception.BusinessException;
import com.talko.exception.ErrorCode;

public class InvalidCredentialsException extends BusinessException {
  public InvalidCredentialsException() {
    super(ErrorCode.INVALID_CREDENTIALS);
  }

  public InvalidCredentialsException(String message) {
    super(ErrorCode.INVALID_CREDENTIALS, message);
  }
}
