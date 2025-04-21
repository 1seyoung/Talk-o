package com.talko.controller;

import com.talko.dto.request.CheckEmailRequestDto;
import com.talko.dto.request.UserSignupRequestDto;
import com.talko.dto.response.UserSignupResponseDto;
import com.talko.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

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
