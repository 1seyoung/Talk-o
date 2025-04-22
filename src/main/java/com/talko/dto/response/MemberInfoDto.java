package com.talko.dto.response;


import com.talko.domain.type.RoomRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoDto {
  private Long userId;
  private String name;
  private RoomRole role;

  public static MemberInfoDto of(Long userId, String name, RoomRole role) {
    return MemberInfoDto.builder()
        .userId(userId)
        .name(name)
        .role(role)
        .build();
  }
}
