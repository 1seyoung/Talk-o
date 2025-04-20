package com.talko.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

  private final ErrorCode errorCode;

  /**
   * Constructs a new exception with the specified error code, using the error code's message as the exception message.
   *
   * @param errorCode the error code representing the specific error condition
   */
  protected BaseException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  /**
   * Constructs a new exception with the specified error code and detail message.
   *
   * @param errorCode the error code representing the specific error condition
   * @param message the detail message for this exception
   */
  protected BaseException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  /**
   * Constructs a new exception with the specified error code, detail message, and cause.
   *
   * @param errorCode the error code representing the specific error condition
   * @param message the detail message for the exception
   * @param cause the underlying cause of the exception
   */
  protected BaseException(ErrorCode errorCode, String message, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }
}