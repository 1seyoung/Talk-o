package com.talko.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Room {
  private Long id;
  private String name;
  private Long hostId;
  private int participantCount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Room(Long id, String name, Long hostId, int participantCount) {
    this.id = id;
    this.name = name;
    this.hostId = hostId;
    this.participantCount = participantCount;
  }

  public static Room of(String name, User host) {
    return new Room(null, name, host.getId(), 1);
  }

  public void changeName(String newName) {
    this.name = newName;
  }

  public boolean isHost(Long userId) {
    if(!this.hostId.equals(userId)) {
      return false;
    } else {
      return true;
    }
  }

  public void addMember() {
    this.participantCount++;
  }
}
