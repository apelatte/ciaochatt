package com.chat.ciao.services;

import com.chat.ciao.dto.MessageDTO;
import com.chat.ciao.models.Message;

import java.util.List;

public interface iMessageService {

  List<Message> findAll();
  Message findById(long id);
  Message save(Message message);
  void delete(Message message);
}
