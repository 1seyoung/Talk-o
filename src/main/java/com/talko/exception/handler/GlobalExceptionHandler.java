package com.talko.exception.handler;

import com.talko.exception.BaseException;
import com.talko.exception.BusinessException;
import com.talko.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(getClass());

  /**
   * Handles BusinessException instances thrown by controllers and returns a standardized error response.
   *
   * Constructs a ResponseEntity containing an ErrorResponse with the error code, exception message, and request URI.
   * Logs the error if the associated HTTP status is a server error (5xx).
   *
   * @param e the exception containing error details
   * @param request the HTTP request that triggered the exception
   * @return a ResponseEntity with the appropriate HTTP status and error response body
   */
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(BaseException e, HttpServletRequest request) {
    if (e.getErrorCode().getStatus().is5xxServerError()) {
      log.error("Business Error: {}", e.getMessage(), e);
    }
    return ResponseEntity
        .status(e.getErrorCode().getStatus())
        .body(new ErrorResponse(
            e.getErrorCode().getCode(),
            e.getMessage(),
            request.getRequestURI()
        ));
  }
}
