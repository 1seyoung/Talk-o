package com.talko.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  // Common
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C500", "서버 내부 오류가 발생했습니다");


  private final HttpStatus status;
  private final String code;
  private final String message;

  /**
   * Constructs an ErrorCode enum constant with the specified HTTP status, error code, and message.
   *
   * @param status the HTTP status associated with the error
   * @param code the custom error code string
   * @param message the descriptive error message
   */
  ErrorCode(HttpStatus status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }
}
