package com.talko.web.dto.response;

import com.talko.domain.Room;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomSimpleResponseDto {
  private Long id;
  private String name;
  private int participantCount;

  public static RoomSimpleResponseDto from(Room room) {
    return RoomSimpleResponseDto.builder()
        .id(room.getId())
        .name(room.getName())
        .participantCount(room.getParticipantCount())
        .build();
  }
}