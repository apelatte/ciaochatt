package com.chat.ciao.services.impl;

import com.chat.ciao.dao.iUserDao;
import com.chat.ciao.models.User;
import com.chat.ciao.services.iUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements iUserService {

  @Autowired
  private iUserDao userDao;

  @Override
  public List<User> getAll() {
    return this.userDao.findAll();
  }

  @Override
  public User getById(long id) {
    return this.userDao.findById(id).orElse(null);
  }

  @Override
  public User getByUsername(String username) {
    return this.userDao.findByUsername(username).orElse(null);
  }

  @Override
  public User save(User user) {
    return this.userDao.save(user);
  }

  @Override
  public User delete(User user) {
    this.userDao.delete(user);
    return user;
  }
}
