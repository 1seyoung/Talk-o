package com.talko.web.controller;
import com.talko.common.annotation.Auth;
import com.talko.domain.type.AuthInfo;
import com.talko.web.dto.response.InvitationResponseDto;
import com.talko.web.service.InvitationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/invitations")
public class InvitationController {

  private final InvitationService invitationService;

  @GetMapping
  public ResponseEntity<List<InvitationResponseDto>> getMyInvitations(@Auth AuthInfo authInfo) {
    List<InvitationResponseDto> invitations = invitationService.getMyInvitations(authInfo);
    return ResponseEntity.ok(invitations);
  }

  @GetMapping("/{inviteId}")
  public ResponseEntity<InvitationResponseDto> getInvitationDetail(@PathVariable Long inviteId, @Auth AuthInfo authInfo) {
    InvitationResponseDto response = invitationService.getInvitationDetail(inviteId, authInfo);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/{inviteId}/accept")
  public ResponseEntity<Void> acceptInvitation(@PathVariable Long inviteId, @Auth AuthInfo authInfo) {
    invitationService.acceptInvitation(inviteId, authInfo);
    return ResponseEntity.ok().build();
  }


  @PostMapping("/{inviteId}/reject")
  public ResponseEntity<Void> rejectInvitation(@PathVariable Long inviteId, @Auth AuthInfo authInfo) {
    invitationService.rejectInvitation(inviteId, authInfo);
    return ResponseEntity.ok().build();
  }
}
