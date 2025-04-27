package com.talko.web.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
  private final LocalDateTime timestamp;
  private final String code;
  private final String message;
  private final String path;
  private final List<FieldError> errors;

  public ErrorResponse(String code, String message, String path) {
    this(code, message, path, new ArrayList<>());
  }

  public ErrorResponse(String code, String message, String path, List<FieldError> errors) {
    this(LocalDateTime.now(), code, message, path, errors);
  }

  @Getter
  @ToString
  @AllArgsConstructor
  public static class FieldError {
    private final String field;
    private final Object value;
    private final String reason;
  }
}