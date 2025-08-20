package com.talko.domain.type;

import lombok.Getter;

@Getter
public class AuthInfo {

  private final String email;
  private final Long userId;
  private final String name;

  public AuthInfo(String email, Long userId, String name) {
    this.email = email;
    this.userId = userId;
    this.name = name;
  }
}