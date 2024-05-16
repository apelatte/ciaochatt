package com.chat.ciao.dao;

import com.chat.ciao.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface iUserDao extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);
}
