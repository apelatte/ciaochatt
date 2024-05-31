package com.chat.ciao.dao;

import com.chat.ciao.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface iRolDao extends JpaRepository<Rol, Long> {

  Optional<Rol> findByRolName(String rolName);
}
