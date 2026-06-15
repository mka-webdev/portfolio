package com.uni.app.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.uni.app.Application.Model.Message;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderAndRecipientOrSenderAndRecipientOrderBySentAtAsc(
            String sender1, String recipient1,
            String sender2, String recipient2);
    List<Message> findBySenderOrderBySentAtAsc(@Param("username") String username);
    List<Message> findBySenderOrRecipient(@Param("username") String username, @Param("username") String me);

}