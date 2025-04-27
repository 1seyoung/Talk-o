package com.talko.web.dto.response;

import com.talko.domain.Invitation;
import com.talko.domain.type.InvitationStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InvitationResponseDto {
  private Long invitationId;
  private Long chatroomId;
  private Long inviterId;
  private Long inviteeId;
  private InvitationStatus status; // pending, accepted, rejected
  private LocalDateTime invitedAt;
  private LocalDateTime respondedAt;

  public static InvitationResponseDto from(Invitation invitation) {
    return InvitationResponseDto.builder()
        .invitationId(invitation.getId())
        .chatroomId(invitation.getChatroomId())
        .inviterId(invitation.getInviterId())
        .inviteeId(invitation.getInviteeId())
        .status(invitation.getStatus())
        .invitedAt(invitation.getInvitedAt())
        .respondedAt(invitation.getRespondedAt())
        .build();
  }
}
