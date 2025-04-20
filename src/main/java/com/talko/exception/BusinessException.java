package com.talko.exception;

import lombok.Getter;

@Getter
public class BusinessException extends BaseException {

  /**
   * Constructs a BusinessException with the specified error code.
   *
   * @param errorCode the error code representing the business logic error
   */
  public BusinessException(ErrorCode errorCode) {
    super(errorCode);
  }

  /**
   * Constructs a BusinessException with the specified error code and custom message.
   *
   * @param errorCode the error code representing the business error
   * @param message the custom detail message for the exception
   */
  public BusinessException(ErrorCode errorCode, String message) {
    super(errorCode, message);
  }
}
