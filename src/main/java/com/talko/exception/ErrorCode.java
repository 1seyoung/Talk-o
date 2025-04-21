package com.talko.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  // Auth
  INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "A401", "이메일 또는 비밀번호가 일치하지 않습니다"),

  // User
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U404", "사용자를 찾을 수 없습니다."),

  // Validation
  INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "V001", "비밀번호는 필수 입력값입니다."),

  //DB
  DATABASE_INSERT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "D500", "데이터 저장 중 오류가 발생했습니다"),

  // Common
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C500", "서버 내부 오류가 발생했습니다");


  private final HttpStatus status;
  private final String code;
  private final String message;

  ErrorCode(HttpStatus status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }
}
