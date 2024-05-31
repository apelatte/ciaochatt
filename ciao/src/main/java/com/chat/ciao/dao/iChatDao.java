package com.chat.ciao.dao;

import com.chat.ciao.models.Chat;
import com.chat.ciao.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface iChatDao extends JpaRepository<Chat, Long> {

}
