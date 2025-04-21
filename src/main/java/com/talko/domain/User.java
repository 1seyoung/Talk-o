package com.talko.domain;

import lombok.Getter;

@Getter
public class User {

  private Long id;
  private final String email;
  private String password;
  private String name;

  public User(Long id, String email, String password, String name) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
  }

  public static User of(String email, String password, String name) {
    return new User(null, email, password, name);
  }
}
