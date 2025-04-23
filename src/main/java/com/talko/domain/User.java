package com.talko.domain;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class User {

  private Long id;
  private final String email;
  private String password;
  private String name;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


  public User(Long id, String email, String password, String name) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
  }

  public User(Long id, String email, String password, String name,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }


  public static User of(String email, String password, String name) {
    return new User(null, email, password, name);
  }
}
