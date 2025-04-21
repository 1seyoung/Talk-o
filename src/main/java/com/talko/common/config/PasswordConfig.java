package com.talko.common.config;

import com.talko.exception.valid.PasswordValidationException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordConfig {

  @Bean
  public TalkoPasswordEncoder passwordEncoder() {
    return new CustomTalkoPasswordEncoder();
  }

  public interface TalkoPasswordEncoder {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
  }

  public static class CustomTalkoPasswordEncoder implements TalkoPasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
      if (rawPassword == null || rawPassword.toString().trim().isEmpty()) {
        throw new PasswordValidationException();
      }
      return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
      return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
  }

}
