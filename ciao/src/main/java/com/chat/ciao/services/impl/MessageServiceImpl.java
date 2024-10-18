package com.chat.ciao.services.impl;

import com.chat.ciao.dao.iMessageDao;
import com.chat.ciao.dto.MessageDTO;
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

  @Override
  public MessageDTO mapToDTO(Message message) {
    MessageDTO response = new MessageDTO();
    response.setFromID(message.getFrom().getId());
    response.setTime(message.getTime());
    response.setText(message.getText());
    response.setToID(message.getTo().getId());
    return response;
  }
}
