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

  public User toDomain(String encodedPassword) {
    return User.of(email, encodedPassword, name);
  }
}
