package com.talko.dto.response;

import com.talko.domain.Invitation;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InviteResponseDto {
  private Long invitationId;
  private Long chatroomId;
  private Long inviteeId;
  private String status;
  private LocalDateTime invitedAt;

  public static InviteResponseDto from(Invitation invitation) {
    return InviteResponseDto.builder()
        .invitationId(invitation.getId())
        .chatroomId(invitation.getChatroomId())
        .inviteeId(invitation.getInviteeId())
        .status(invitation.getStatus().name())
        .invitedAt(invitation.getInvitedAt())
        .build();
  }
}


