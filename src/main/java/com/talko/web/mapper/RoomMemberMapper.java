package com.talko.web.mapper;

import com.talko.domain.RoomMember;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoomMemberMapper {

  @Insert("""
        INSERT INTO room_members (chatroom_id, user_id, role)
        VALUES (#{chatroomId}, #{userId}, #{role})
    """)
  void insertRoomMember(
      @Param("chatroomId") Long chatroomId,
      @Param("userId") Long userId,
      @Param("role") String role
  );

  @Select("""
    SELECT chatroom_id
    FROM room_members
    WHERE user_id = #{userId}
""")
  List<Long> findRoomIdsByUserId(Long userId);

  @Select("""
    SELECT id, user_id, chatroom_id, role, joined_at
    FROM room_members
    WHERE chatroom_id = #{roomId}
""")
  List<RoomMember> findByRoomId(Long roomId);

  @Select("""
    SELECT EXISTS (
      SELECT 1
      FROM room_members
      WHERE chatroom_id = #{roomId} AND user_id = #{userId}
    )
""")
  boolean existsByRoomIdAndUserId(@Param("roomId") Long roomId, @Param("userId") Long userId);
}
