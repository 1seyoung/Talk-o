package com.talko.web.mapper;

import com.talko.domain.Room;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoomMapper {


  @Insert("INSERT INTO chatrooms (name, host_id, participant_count) VALUES (#{name}, #{hostId}, #{participantCount})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertRoom(Room newRoom);



  @Select("""
    SELECT id, name, host_id, participant_count, created_at, updated_at
    FROM chatrooms
    WHERE id = #{roomId}
""")
  Room findById(Long roomId);

  List<Room> findByIds(@Param("ids") List<Long> ids);
}
