package com.talko.exception;

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

  /**
   * Creates an ErrorResponse with the specified error code, message, and path, initializing with no field-specific errors.
   *
   * @param code the error code identifier
   * @param message the descriptive error message
   * @param path the request path or context where the error occurred
   */
  public ErrorResponse(String code, String message, String path) {
    this(code, message, path, new ArrayList<>());
  }

  /**
   * Creates an ErrorResponse with the specified error code, message, request path, and list of field errors.
   * The timestamp is set to the current time.
   *
   * @param code the error code identifier
   * @param message a descriptive error message
   * @param path the request path or context where the error occurred
   * @param errors a list of field-specific error details
   */
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