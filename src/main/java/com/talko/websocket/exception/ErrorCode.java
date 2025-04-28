package com.talko.websocket.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  UNAUTHORIZED("W401", "사용자 인증 정보가 없습니다."),
  ROOM_NOT_FOUND("W404", "존재하지 않는 채팅방입니다."),
  INVALID_MESSAGE("W400", "메시지 형식이 올바르지 않습니다.");

  private final String code;
  private final String message;
}
