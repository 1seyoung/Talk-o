package com.talko.mapper;

import com.talko.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Insert("INSERT INTO users (email, password, name) VALUES (#{email}, #{password}, #{name})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  int insertUser(User user);

  @Select("SELECT EXISTS (SELECT 1 FROM users WHERE email = #{email})")
  boolean existsByEmail(String email);
}
