package com.talko.controller;
import com.talko.common.annotation.Auth;
import com.talko.domain.type.AuthInfo;
import com.talko.dto.response.InvitationResponseDto;
import com.talko.service.InvitationService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invitations")
public class InvitationController {

  private final InvitationService invitationService;

  public InvitationController(InvitationService invitationService) {
    this.invitationService = invitationService;
  }

  @GetMapping //초대 받은 목록 확인
  public ResponseEntity<List<InvitationResponseDto>> getMyInvitations(@Auth AuthInfo authInfo) {
    List<InvitationResponseDto> invitations = invitationService.getMyInvitations(authInfo);
    return ResponseEntity.ok(invitations);
  }

  @GetMapping("/{inviteId}") // 초대하나 확인
  public ResponseEntity<InvitationResponseDto> getInvitationDetail(@PathVariable Long inviteId, @Auth AuthInfo authInfo) {
    InvitationResponseDto response = invitationService.getInvitationDetail(inviteId, authInfo);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/{inviteId}/accept") //초대 수락
  public ResponseEntity<Void> acceptInvitation(@PathVariable Long inviteId, @Auth AuthInfo authInfo) {
    invitationService.acceptInvitation(inviteId, authInfo);
    return ResponseEntity.ok().build();
  }


  @PostMapping("/{inviteId}/reject") //초대 거절
  public ResponseEntity<Void> rejectInvitation(@PathVariable Long inviteId, @Auth AuthInfo authInfo) {
    invitationService.rejectInvitation(inviteId, authInfo);
    return ResponseEntity.ok().build();
  }
}
