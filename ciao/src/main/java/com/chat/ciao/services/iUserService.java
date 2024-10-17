package com.chat.ciao.services;

import com.chat.ciao.dto.UserDTO;
import com.chat.ciao.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface iUserService {

  List<User> getAll();
  User getById(long id);
  User getByUsername(String username);
  User save(User user);
  User delete(User user);
  UserDTO mapToDTO(User user);
}
