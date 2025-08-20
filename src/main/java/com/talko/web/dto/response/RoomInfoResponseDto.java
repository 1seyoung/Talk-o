package com.talko.web.dto.response;

import com.talko.domain.Room;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomInfoResponseDto {
  private Long id;
  private String name;
  private String hostName;
  private int participantCount;
  private List<MemberInfoDto> members;

  public static RoomInfoResponseDto from(Room room, List<MemberInfoDto> members, String hostName) {
    return RoomInfoResponseDto.builder()
        .id(room.getId())
        .name(room.getName())
        .hostName(hostName)
        .participantCount(room.getParticipantCount())
        .members(members)
        .build();
  }
}