package com.talko.web.service;

import com.talko.common.config.PasswordConfig.TalkoPasswordEncoder;
import com.talko.domain.User;
import com.talko.web.dto.request.UserSignupRequestDto;
import com.talko.web.dto.response.UserSignupResponseDto;
import com.talko.web.exception.db.DatabaseInsertFailedException;
import com.talko.web.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final TalkoPasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  public UserService(TalkoPasswordEncoder passwordEncoder, UserMapper userMapper) {
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
  }

  public UserSignupResponseDto signup(UserSignupRequestDto request) {
    String encodePassword = passwordEncoder.encode(request.getPassword());
    User member = request.toDomain(encodePassword);

    int rowsAffected = userMapper.insertUser(member);
    if (rowsAffected != 1) {
      throw new DatabaseInsertFailedException("회원가입 중 users 테이블 insert 실패");
    }

    return UserSignupResponseDto.from(member);
  }

  public boolean emailExists(String email) {
    return userMapper.existsByEmail(email);
  }

}
