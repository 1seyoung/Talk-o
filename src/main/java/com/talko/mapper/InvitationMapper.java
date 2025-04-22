package com.talko.mapper;

import com.talko.domain.Invitation;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface InvitationMapper {

  @Insert("""
        INSERT INTO invitations (
            chatroom_id,
            inviter_id,
            invitee_id,
            status,
            invited_at
        ) VALUES (
            #{chatroomId},
            #{inviterId},
            #{inviteeId},
            #{status},
            #{invitedAt}
        )
    """)
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertInvitation(Invitation invitation);

  @Select("""
        SELECT id, chatroom_id, inviter_id, invitee_id, status, invited_at, responded_at
        FROM invitations
        WHERE invitee_id = #{userId}
    """)
  List<Invitation> findAllByInviteeId(Long userId);

  @Select("""
    SELECT id, chatroom_id, inviter_id, invitee_id, status, invited_at, responded_at
    FROM invitations
    WHERE id = #{inviteId}
    """)
  Invitation findById(Long inviteId);

  @Update("""
    UPDATE invitations
    SET status = #{status},
        responded_at = #{respondedAt}
    WHERE id = #{id}
""")
  void update(Invitation invitation);
}
