package com.talko.exception.valid;

import com.talko.exception.ErrorCode;
import com.talko.exception.ValidException;

public class PasswordValidationException extends ValidException {
  public PasswordValidationException() {
    super(ErrorCode.INVALID_PASSWORD);
  }

  public PasswordValidationException(String message) {
    super(ErrorCode.INVALID_PASSWORD, message);
  }
}
