package com.talko.websocket.utils;

import com.talko.domain.type.AuthInfo;
import com.talko.websocket.exception.error.UnauthorizedException;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Component
public class WebSocketAuthUtils {
  public static AuthInfo getAuthInfo(SimpMessageHeaderAccessor headerAccessor) {
    AuthInfo authInfo = (AuthInfo) headerAccessor.getSessionAttributes().get("authInfo");

    if (authInfo == null) {
      throw new UnauthorizedException("WebSocket 인증 정보가 없습니다.");
    }
    return authInfo;
  }
}
