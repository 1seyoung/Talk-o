package com.talko.domain.type;

import lombok.Getter;

@Getter
public class AuthInfo {

  private final String email;
  private final Long userId;

  public AuthInfo(String email, Long userId) {
    this.email = email;
    this.userId = userId;
  }
}