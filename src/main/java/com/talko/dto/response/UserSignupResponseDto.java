package com.talko.dto.response;

import com.talko.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserSignupResponseDto {
  private String email;
  private String name;

  /**
   * Creates a UserSignupResponseDto from a given User object.
   *
   * @param member the User whose email and name will be used
   * @return a new UserSignupResponseDto containing the user's email and name
   */
  public static UserSignupResponseDto from(User member) {
    return new UserSignupResponseDto(
        member.getEmail(),
        member.getName()
    );
  }
}
