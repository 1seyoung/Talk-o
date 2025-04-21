package com.talko.dto.response;


import com.talko.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {

  private String accessToken;
  private String email;
  private String name;
  private Long userId;

  public static LoginResponseDto from(String accessToken, User user) {
    return new LoginResponseDto(
        accessToken,
        user.getEmail(),
        user.getName(),
        user.getId()
    );
  }
}