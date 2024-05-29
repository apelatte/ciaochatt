package com.chat.ciao.services;

import com.chat.ciao.models.Chat;
import com.chat.ciao.models.User;

import java.util.List;

public interface iChatService {

  List<Chat> findAll();
  Chat findById(long id);
  List<Chat> findChatsByUser(User user);
  Chat save(Chat chat);
  void delete(Chat chat);
}
