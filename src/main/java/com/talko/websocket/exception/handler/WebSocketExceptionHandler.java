package com.talko.websocket.exception.handler;

import com.talko.websocket.exception.WebSocketErrorResponse;
import com.talko.websocket.exception.WebSocketException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebSocketExceptionHandler {

  @MessageExceptionHandler(WebSocketException.class)
  @SendToUser("/queue/errors")
  public WebSocketErrorResponse handleWebSocketException(WebSocketException ex, SimpMessageHeaderAccessor accessor) {
    String destination = accessor.getDestination();
    log.warn("WebSocket 예외 발생: {} - {}", ex.getErrorCode(), ex.getMessage());
    return new WebSocketErrorResponse(ex.getErrorCode().getCode(), ex.getMessage(),destination);
  }

  @MessageExceptionHandler(Exception.class)
  @SendToUser("/queue/errors")
  public WebSocketErrorResponse handleGeneralException(Exception ex, SimpMessageHeaderAccessor accessor) {
    String destination = accessor.getDestination();
    log.error("예기치 못한 WebSocket 예외 발생", ex);

    return new WebSocketErrorResponse(
        "W500",
        "예기치 못한 오류가 발생했습니다.",
        destination
    );
  }


}
