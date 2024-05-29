package com.chat.ciao.dao;

import com.chat.ciao.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface iMessageDao extends JpaRepository<Message, Long> {

  Optional<List<Message>> findByChatId(long id);
}
