package com.chat.ciao.services;

import com.chat.ciao.dto.ChatDTO;
import com.chat.ciao.models.Chat;
import com.chat.ciao.models.User;

import java.util.List;

public interface iChatService {

  List<Chat> findAll();
  Chat findById(long id);
  List<ChatDTO> findAllByUserId(long id);
  Chat findByParticipants(List<User> participants);
  Chat save(Chat chat);
  void delete(Chat chat);
}
