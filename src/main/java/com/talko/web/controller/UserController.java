package com.talko.web.controller;

import com.talko.web.dto.request.CheckEmailRequestDto;
import com.talko.web.dto.request.UserSignupRequestDto;
import com.talko.web.dto.response.UserSignupResponseDto;
import com.talko.web.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<UserSignupResponseDto> signupUser(
      @Valid @RequestBody UserSignupRequestDto request) {

    UserSignupResponseDto response = userService.signup(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/check-email")
  public ResponseEntity<Boolean> checkEmailExists(CheckEmailRequestDto request) {
    boolean exists = userService.emailExists(request.getEmail());
    return ResponseEntity.ok(exists);
  }

}
