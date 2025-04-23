package com.talko.exception;

import lombok.Getter;

@Getter
public class ValidException extends BaseException {

  public ValidException(ErrorCode errorCode) {
    super(errorCode);
  }

  public ValidException(ErrorCode errorCode, String message) {
    super(errorCode, message);
  }
}