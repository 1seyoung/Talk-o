package com.talko.controller;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talko.common.config.PasswordConfig.TalkoPasswordEncoder;
import com.talko.domain.User;
import com.talko.dto.request.UserSignupRequestDto;
import com.talko.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private UserMapper userMapper;
  @Autowired private TalkoPasswordEncoder passwordEncoder;


  @Test
  void checkEmailExists_shouldReturnTrue_whenEmailAlreadyExists() throws Exception {
    String email = "existing3@example.com";
    mockMvc.perform(get("/api/user/check-email")
            .param("email", email))
        .andExpect(status().isOk())
        .andExpect(content().string("true"));
  }

  @Test
  void signupUser_shouldSucceed_withValidInput() throws Exception {
    String plainPassword = "12345678";
    String email = "test_user1@example.com";
    var request = new UserSignupRequestDto(
        email,
        plainPassword,
        "테스터"
    );

    mockMvc.perform(post("/api/user/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value("test_user1@example.com"))
        .andExpect(jsonPath("$.name").value("테스터"));

    User savedUser = userMapper.findUserByEmail(email);
    assertNotEquals(plainPassword, savedUser.getPassword());
    assertTrue(passwordEncoder.matches(plainPassword, savedUser.getPassword()));

  }

}