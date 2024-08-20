package com.chat.ciao.services.impl;

import com.chat.ciao.dao.iChatDao;
import com.chat.ciao.models.Chat;
import com.chat.ciao.models.User;
import com.chat.ciao.services.iChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements iChatService {

  @Autowired
  private iChatDao chatDao;

  @Override
  public List<Chat> findAll() {
    return this.chatDao.findAll();
  }

  @Override
  public Chat findById(long id) {
    return this.chatDao.findById(id).orElse(null);
  }

  @Override
  public List<Chat> findAllByUserId(long id) {
    return this.chatDao.findAllByParticipantsId(id).orElse(null);
  }

  @Override
  public Chat save(Chat chat) {
    return this.chatDao.save(chat);
  }

  @Override
  public void delete(Chat chat) {
    this.chatDao.delete(chat);
  }
}
