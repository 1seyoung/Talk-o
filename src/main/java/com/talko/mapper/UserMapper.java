package com.talko.mapper;

import com.talko.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  /**
   * Inserts a new user record into the database and sets the generated primary key on the user object.
   *
   * @param user the user entity to insert
   * @return the number of rows affected by the insert operation
   */
  @Insert("INSERT INTO users (email, password, name) VALUES (#{email}, #{password}, #{name})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  int insertUser(User user);

  /****
   * Checks if a user with the specified email exists in the database.
   *
   * @param email the email address to check for existence
   * @return true if a user with the given email exists, false otherwise
   */
  @Select("SELECT EXISTS (SELECT 1 FROM users WHERE email = #{email})")
  boolean existsByEmail(String email);
}
