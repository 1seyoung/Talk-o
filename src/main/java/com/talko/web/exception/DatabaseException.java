package com.talko.web.exception;

import lombok.Getter;

@Getter
public class DatabaseException extends BaseException {

  public DatabaseException(ErrorCode errorCode) {
    super(errorCode);
  }

  public DatabaseException(ErrorCode errorCode, String message) {
    super(errorCode, message);
  }

  public DatabaseException(ErrorCode errorCode, String message, Throwable cause) {
    super(errorCode, message, cause);
  }
}
