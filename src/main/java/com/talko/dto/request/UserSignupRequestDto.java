package com.talko.dto.request;

import com.talko.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequestDto {
  private String email;
  private String password;
  private String name;

  /**
   * Converts this signup request DTO into a domain User object using the provided encoded password.
   *
   * @param encodedPassword the user's password after encoding
   * @return a new User instance with the email, encoded password, and name from this DTO
   */
  public User toDomain(String encodedPassword) {
    return User.of(email, encodedPassword, name);
  }
}
