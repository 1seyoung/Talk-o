package com.talko.web.dto.request;

import com.talko.domain.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequestDto {
  @NotNull(message = "이메일은 필수입니다.")
  private String email;

  @NotNull(message = "비밀번호는 필수입니다.")
  @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다")
  private String password;

  @NotNull(message = "이름은 필수입니다.")
  private String name;

  public User toDomain(String encodedPassword) {
    return User.of(email, encodedPassword, name);
  }
}
