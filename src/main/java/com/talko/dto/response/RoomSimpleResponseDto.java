package com.talko.dto.response;

import com.talko.domain.Room;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;
import org.apache.logging.log4j.message.Message;

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