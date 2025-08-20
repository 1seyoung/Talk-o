package com.talko.websocket.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

  //TODO : Redis로 변경 ( 이유 : 서버 재시작 시 데이터 소실 방지 및 성능 향상)

  private final Map<String, Long> sessionRoomMap = new ConcurrentHashMap<>();

  private final Map<String, Long> sessionUserMap = new ConcurrentHashMap<>();

  public void enterRoom(Long roomId, String sessionId, Long userId) {
    sessionRoomMap.put(sessionId, roomId);
    sessionUserMap.put(sessionId, userId);
  }

  public void leaveRoom(String sessionId) {
    sessionRoomMap.remove(sessionId);
    sessionUserMap.remove(sessionId);
  }

  public Long getRoomId(String sessionId) {
    return sessionRoomMap.get(sessionId);
  }

  public Long getUserId(String sessionId) {
    return sessionUserMap.get(sessionId);
  }
}
