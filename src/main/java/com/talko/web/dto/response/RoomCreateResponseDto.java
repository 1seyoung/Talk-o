package com.talko.web.dto.response;

import com.talko.domain.Room;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomCreateResponseDto {
  private Long id;
  private String name;
  private Long hostId;
  private int participantCount;
  private LocalDateTime createdAt;

  public static RoomCreateResponseDto from(Room room) {
    return RoomCreateResponseDto.builder()
        .id(room.getId())
        .name(room.getName())
        .hostId(room.getHostId())
        .participantCount(room.getParticipantCount())
        .createdAt(room.getCreatedAt())
        .build();
  }
}
