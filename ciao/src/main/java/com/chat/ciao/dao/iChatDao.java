package com.chat.ciao.dao;

import com.chat.ciao.models.Chat;
import com.chat.ciao.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface iChatDao extends JpaRepository<Chat, Long> {

  Optional<List<Chat>> findByParticipantsLike(User user);
}
