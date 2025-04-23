package com.talko.domain;

import com.talko.domain.type.RoomRole;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomMember {
  private Long id;
  private Long userId;
  private Long chatroomId;
  private RoomRole role;
  private LocalDateTime joinedAt;

  public static RoomMember of(Long userId, Long chatroomId, RoomRole role) {
    return RoomMember.builder()
        .userId(userId)
        .chatroomId(chatroomId)
        .role(role)
        .joinedAt(LocalDateTime.now())
        .build();
  }
}
