package com.talko.web.mapper;

import com.talko.domain.User;
import com.talko.web.dto.response.UserNameDto;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Insert("INSERT INTO users (email, password, name) VALUES (#{email}, #{password}, #{name})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  int insertUser(User user);

  @Select("SELECT EXISTS (SELECT 1 FROM users WHERE email = #{email})")
  boolean existsByEmail(String email);

  @Select("SELECT * FROM users WHERE email = #{email}")
  User findUserByEmail(String email);

  @Select("SELECT * FROM users WHERE id = #{userId}")
  User findById(Long userId);

  @MapKey("id")
  Map<Long, UserNameDto> findNamesByIds(@Param("list") List<Long> ids);

}
