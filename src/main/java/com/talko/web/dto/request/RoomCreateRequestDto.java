package com.talko.web.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomCreateRequestDto {

  @NotNull(message = "방 이름은 필수입니다.")
  private String name;
}
