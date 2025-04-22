package com.talko.domain;

import com.talko.domain.type.InvitationStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Invitation {
  private Long id;
  private Long chatroomId;
  private Long inviterId;
  private Long inviteeId;
  private InvitationStatus status;
  private LocalDateTime invitedAt;
  private LocalDateTime respondedAt;

  public static Invitation of(Long chatroomId, Long inviterId, Long inviteeId) {
    return Invitation.builder()
        .chatroomId(chatroomId)
        .inviterId(inviterId)
        .inviteeId(inviteeId)
        .status(InvitationStatus.PENDING)
        .invitedAt(LocalDateTime.now())
        .build();
  }

  public boolean isInvitee(Long userId) {
    return this.inviteeId.equals(userId);
  }

  private boolean isPending() {
    return this.status.equals("PENDING");
  }
  public void accept() {
    if(!isPending()) {
      throw new IllegalStateException("Already accepted or rejected invitation");
    }
    this.status = InvitationStatus.ACCEPTED;
    this.respondedAt = LocalDateTime.now();
  }

  public void reject() {
    if(!isPending()) {
      throw new IllegalStateException("Already accepted or rejected invitation");
    }
    this.status = InvitationStatus.REJECTED;
    this.respondedAt = LocalDateTime.now();
  }
}
