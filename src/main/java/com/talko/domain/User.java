package com.talko.domain;

import lombok.Getter;

@Getter
public class User {

  private Long id;
  private final String email;
  private String password;
  private String name;

  /**
   * Constructs a new User with the specified id, email, password, and name.
   *
   * @param id the unique identifier for the user, or null if not assigned
   * @param email the user's email address
   * @param password the user's password
   * @param name the user's name
   */
  public User(Long id, String email, String password, String name) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
  }

  /**
   * Creates a new User instance with the specified email, password, and name, and a null id.
   *
   * @param email the user's email address
   * @param password the user's password
   * @param name the user's name
   * @return a new User with the given email, password, and name, and a null id
   */
  public static User of(String email, String password, String name) {
    return new User(null, email, password, name);
  }
}
