package com.chat.ciao;

import com.chat.ciao.dao.iRolDao;
import com.chat.ciao.models.Rol;
import com.chat.ciao.models.RolEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CiaoChattApplication {

  @Autowired
  private iRolDao rolDao;

	public static void main(String[] args) {
		SpringApplication.run(CiaoChattApplication.class, args);
	}

  @Bean
  CommandLineRunner init(){
    return args -> {
      if(rolDao.count() == 0) {
        Rol adminRol = new Rol(RolEnum.ADMIN);
        Rol userRol = new Rol(RolEnum.USER);
        Rol invitedRol = new Rol(RolEnum.INVITED);
        Rol developerRol = new Rol(RolEnum.DEVELOPER);
        this.rolDao.saveAll(List.of(adminRol, userRol, invitedRol, developerRol));
      }
    };
  }
}
