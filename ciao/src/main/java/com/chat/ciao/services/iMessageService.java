package com.chat.ciao.services;

import com.chat.ciao.models.Message;

import java.util.List;

public interface iMessageService {

  List<Message> findAll();
  List<Message> findAllByChat(long chatID);
  Message findById(long id);
  Message save(Message message);
  void delete(Message message);
}
