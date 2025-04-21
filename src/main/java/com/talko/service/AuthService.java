package com.talko.service;

import com.talko.common.annotation.Auth;
import com.talko.common.config.PasswordConfig.TalkoPasswordEncoder;
import com.talko.common.jwt.JwtUtil;
import com.talko.domain.User;
import com.talko.dto.request.LoginRequestDto;
import com.talko.dto.response.LoginResponseDto;
import com.talko.dto.type.AuthInfo;
import com.talko.exception.business.InvalidCredentialsException;
import com.talko.exception.business.UserNotFoundException;
import com.talko.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final UserMapper userMapper;
  private final TalkoPasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  public AuthService(UserMapper userMapper, TalkoPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
  }

  public LoginResponseDto authenticate(LoginRequestDto request) {
    User user = userMapper.findUserByEmail(request.getEmail());

    if(user == null) {
      throw new InvalidCredentialsException("Invalid email or password");
    }

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new RuntimeException("Invalid Password");
    }

    String accessToken = jwtUtil.createAccessToken(
        user.getEmail(),
        user.getId()
    );

    return LoginResponseDto.from(accessToken, user);
  }

  public void logout(@Auth AuthInfo authInfo) {
    // TODO : Implement logout logic
    }

}
