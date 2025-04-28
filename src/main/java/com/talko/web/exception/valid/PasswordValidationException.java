package com.talko.web.exception.valid;

import com.talko.web.exception.ErrorCode;
import com.talko.web.exception.ValidException;

public class PasswordValidationException extends ValidException {
  public PasswordValidationException() {
    super(ErrorCode.INVALID_PASSWORD);
  }

  public PasswordValidationException(String message) {
    super(ErrorCode.INVALID_PASSWORD, message);
  }
}
