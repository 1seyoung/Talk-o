package com.talko.common.resolver;


import com.talko.common.annotation.WsAuth;
import com.talko.domain.type.AuthInfo;
import org.springframework.core.MethodParameter;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Component
public class WsAuthInfoArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterAnnotation(WsAuth.class) != null
        && parameter.getParameterType().equals(AuthInfo.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, Message<?> message) throws Exception {
    StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
    if (accessor == null) {
      throw new IllegalArgumentException("No accessor available");
    }
    Object authInfo = accessor.getSessionAttributes().get("authInfo");
    if (authInfo == null) {
      throw new IllegalArgumentException("authInfo not found in session");
    }
    return authInfo;
  }
}