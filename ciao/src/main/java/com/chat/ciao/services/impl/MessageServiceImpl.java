package com.chat.ciao.services.impl;

import com.chat.ciao.dao.iMessageDao;
import com.chat.ciao.models.Message;
import com.chat.ciao.services.iMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements iMessageService {

  @Autowired
  private iMessageDao messageDao;

  @Override
  public List<Message> findAll() {
    return this.messageDao.findAll();
  }

  @Override
  public List<Message> findAllByChat(long chatID) {
    return this.messageDao.findByChatId(chatID).orElse(null);
  }

  @Override
  public Message findById(long id) {
    return this.messageDao.findById(id).orElse(null);
  }

  @Override
  public Message save(Message message) {
    return this.messageDao.save(message);
  }

  @Override
  public void delete(Message message) {
    this.messageDao.delete(message);
  }
}
