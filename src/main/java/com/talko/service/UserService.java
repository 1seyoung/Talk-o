package com.talko.service;

import com.talko.common.config.PasswordConfig.PasswordEncoder;
import com.talko.domain.User;
import com.talko.dto.request.UserSignupRequestDto;
import com.talko.dto.response.UserSignupResponseDto;
import com.talko.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserMapper memberMapper;

  public UserService(PasswordEncoder passwordEncoder, UserMapper memberMapper) {
    this.passwordEncoder = passwordEncoder;
    this.memberMapper = memberMapper;
  }

  public UserSignupResponseDto signup(UserSignupRequestDto request) {
    String encodePassword = passwordEncoder.encode(request.getPassword());
    User member = request.toDomain(encodePassword);

    int rowsAffected = memberMapper.insertUser(member);
    if (rowsAffected != 1) {
      throw new RuntimeException("회원 가입 실패: DB에 저장되지 않았습니다.");
    }

    return UserSignupResponseDto.from(member);
  }

  public boolean emailExists(String email) {
    return memberMapper.existsByEmail(email);
  }

  }
