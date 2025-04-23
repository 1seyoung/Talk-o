package com.talko.dto.response;

import com.talko.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserSignupResponseDto {
  private String email;
  private String name;

  public static UserSignupResponseDto from(User member) {
    return new UserSignupResponseDto(
        member.getEmail(),
        member.getName()
    );
  }
}
