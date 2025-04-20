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

  /****
   * Constructs a UserService with the specified password encoder and user mapper.
   */
  public UserService(PasswordEncoder passwordEncoder, UserMapper memberMapper) {
    this.passwordEncoder = passwordEncoder;
    this.memberMapper = memberMapper;
  }

  /**
   * Registers a new user by encoding the password, saving the user to the database, and returning a signup response.
   *
   * @param request the user signup request containing user details and raw password
   * @return a response DTO representing the newly registered user
   * @throws RuntimeException if the user could not be saved to the database
   */
  public UserSignupResponseDto signup(UserSignupRequestDto request) {
    String encodePassword = passwordEncoder.encode(request.getPassword());
    User member = request.toDomain(encodePassword);

    int rowsAffected = memberMapper.insertUser(member);
    if (rowsAffected != 1) {
      throw new RuntimeException("회원 가입 실패: DB에 저장되지 않았습니다.");
    }

    return UserSignupResponseDto.from(member);
  }

  /**
   * Checks whether a user with the specified email exists in the database.
   *
   * @param email the email address to check for existence
   * @return true if a user with the given email exists, false otherwise
   */
  public boolean emailExists(String email) {
    return memberMapper.existsByEmail(email);
  }

  }
