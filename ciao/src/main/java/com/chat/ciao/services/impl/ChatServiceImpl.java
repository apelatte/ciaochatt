package com.chat.ciao.services.impl;

import com.chat.ciao.dao.iChatDao;
import com.chat.ciao.dto.ChatDTO;
import com.chat.ciao.dto.MessageDTO;
import com.chat.ciao.dto.UserDTO;
import com.chat.ciao.models.Chat;
import com.chat.ciao.models.User;
import com.chat.ciao.services.iChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
  public List<ChatDTO> findAllByUserId(long id) {
    List<Chat> chatList = this.chatDao.findAllByParticipantsId(id).orElse(null);
    List<ChatDTO> chatDTOS = new ArrayList<>();
    if(chatList != null)
      chatList.forEach(chat -> {
        chatDTOS.add(mapToDTO(chat));
      });
    return chatDTOS;
  }

  @Override
  public Chat findByParticipants(List<User> participants) {
    return this.chatDao.findByParticipants(participants).orElse(null);
  }

  @Override
  public Chat save(Chat chat) {
    return this.chatDao.save(chat);
  }

  @Override
  public void delete(Chat chat) {
    this.chatDao.delete(chat);
  }

  private ChatDTO mapToDTO(Chat chat){
    ChatDTO chatDTO = new ChatDTO();
    chatDTO.setId(chat.getId());
    chat.setLast_update(chat.getLast_update());

    chat.getMessages().forEach(message -> {
      MessageDTO messageDTO = new MessageDTO();
      messageDTO.setText(message.getText());
      messageDTO.setTime(message.getTime());
      messageDTO.setFromID(message.getFrom().getId());
      chatDTO.getMessages().add(messageDTO);
    });

    chat.getParticipants().forEach(participant -> {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(participant.getId());
        userDTO.setAvatar(participant.getAvatar());
        userDTO.setUsername(participant.getUsername());
        chatDTO.getParticipants().add(userDTO);
    });

    return chatDTO;
  }
}
