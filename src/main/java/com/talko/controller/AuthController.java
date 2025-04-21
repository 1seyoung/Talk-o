package com.talko.controller;

import com.talko.common.annotation.Auth;
import com.talko.dto.request.LoginRequestDto;
import com.talko.dto.response.LoginResponseDto;
import com.talko.dto.type.AuthInfo;
import com.talko.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
    LoginResponseDto response = authService.authenticate(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(@Auth AuthInfo authInfo) {
    // TODO : 추후 보안 업그레이드 -> 클라이언트 단에서 토큰 삭제로 해결(나중에 Redis 적용)
    authService.logout(authInfo);
    return ResponseEntity.ok().body("로그아웃 성공");
  }

}
