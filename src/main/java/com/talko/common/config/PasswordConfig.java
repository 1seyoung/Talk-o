package com.talko.common.config;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordConfig {

  /**
   * Provides a Spring bean for password encoding and verification using BCrypt.
   *
   * @return a PasswordEncoder implementation that hashes and verifies passwords with BCrypt
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new CustomPasswordEncoder();
  }

  public interface PasswordEncoder {
    /****
 * Generates a hashed representation of the provided raw password using a secure encoding algorithm.
 *
 * @param rawPassword the plain text password to encode
 * @return the encoded (hashed) password string
 */
String encode(CharSequence rawPassword);
    /****
 * Verifies whether a raw password matches the provided encoded password.
 *
 * @param rawPassword the plain text password to verify
 * @param encodedPassword the previously encoded password to compare against
 * @return true if the raw password matches the encoded password; false otherwise
 */
boolean matches(CharSequence rawPassword, String encodedPassword);
  }

  public static class CustomPasswordEncoder implements PasswordEncoder {

    /**
     * Generates a BCrypt hash of the provided raw password using a randomly generated salt.
     *
     * @param rawPassword the plain text password to encode
     * @return the hashed password in BCrypt format
     */
    @Override
    public String encode(CharSequence rawPassword) {
      return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());
    }

    /**
     * Verifies whether a raw password matches the given BCrypt-encoded password hash.
     *
     * @param rawPassword the raw password to verify
     * @param encodedPassword the BCrypt hash to compare against
     * @return true if the raw password matches the encoded hash; false otherwise
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
      return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
  }

}
