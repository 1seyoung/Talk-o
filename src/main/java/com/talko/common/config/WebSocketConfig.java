package com.talko.common.config;

import com.talko.common.jwt.JwtUtil;
import com.talko.domain.type.AuthInfo;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.unit.DataSize;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * 참고 깃허브 : https://github.com/blue000927/mybatis-practice/blob/feat/chat-system/src/main/java/com/example/mybatispractice/presentation/config/WebSocketConfig.java
 * 참고 : https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-stomp
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  private final JwtUtil jwtUtil;

  private static final int HEARTBEAT_INTERVAL = (int) TimeUnit.SECONDS.toMillis(10);
  private static final int MESSAGE_SIZE_LIMIT = (int) DataSize.ofKilobytes(64).toBytes();
  private static final int SEND_TIME_LIMIT = (int) TimeUnit.SECONDS.toMillis(20);
  private static final int SEND_BUFFER_SIZE_LIMIT = (int) DataSize.ofKilobytes(512).toBytes();

  public WebSocketConfig(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.enableSimpleBroker("/topic", "/queue")
        .setTaskScheduler(webSocketHeartbeatScheduler())
        .setHeartbeatValue(new long[]{HEARTBEAT_INTERVAL, HEARTBEAT_INTERVAL});
    registry.setApplicationDestinationPrefixes("/app");
    registry.setUserDestinationPrefix("/user");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws")
        .setAllowedOriginPatterns("*")
        .withSockJS();
  }

  @Override
  public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
    registration.setMessageSizeLimit(MESSAGE_SIZE_LIMIT)
        .setSendTimeLimit(SEND_TIME_LIMIT)
        .setSendBufferSizeLimit(SEND_BUFFER_SIZE_LIMIT);
  }

  @Override
  public void configureClientInboundChannel(ChannelRegistration registration) {
    registration.interceptors(new ChannelInterceptor() {
      @Override
      public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(
            message, StompHeaderAccessor.class
        );

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
          try {
            // 1. 헤더에서 토큰 추출
            List<String> authorization = accessor.getNativeHeader("Authorization");
            if (authorization == null || authorization.isEmpty()) {
              throw new Exception("Authorization header is missing");
            }

            String token = authorization.get(0);
            if (token.startsWith("Bearer ")) {
              token = token.substring(7);
            }

            // 2. JwtUtil로 토큰 검증
            if (!jwtUtil.validateAccessToken(token)) {
              throw new Exception("Invalid JWT token");
            }

            // 3. JwtUtil로 사용자 정보 추출 (필터와 동일한 방식)
            String email = jwtUtil.extractEmail(token);
            Long userId = jwtUtil.extractUserId(token);
            String name = jwtUtil.extractName(token);

            // 4. 인증 정보 설정 (필터와 동일한 방식)
            AuthInfo authInfo = new AuthInfo(email, userId, name);
            accessor.setUser(() -> email);
            accessor.getSessionAttributes().put("authInfo", authInfo);
            accessor.getSessionAttributes().put("userId", userId);
            accessor.getSessionAttributes().put("name", name);

          } catch (Exception e) {
            throw new MessageDeliveryException("JWT Token validation failed: " + e.getMessage());
          }
        }
        return message;
      }
    });
  }

  private TaskScheduler webSocketHeartbeatScheduler() {
    ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
    scheduler.setPoolSize(1);
    scheduler.setThreadNamePrefix("websocket-heartbeat-thread-");
    scheduler.setDaemon(true);
    scheduler.initialize();
    return scheduler;
  }
}