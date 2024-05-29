package com.chat.ciao.controllers;

import com.chat.ciao.models.Rol;
import com.chat.ciao.models.User;
import com.chat.ciao.services.iRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/roles")
@CrossOrigin(origins = {"http://localhost:4200}"})
public class RolController {

  @Autowired
  private iRolService rolService;

  @GetMapping("/get-all")
  public ResponseEntity<?> getAllRoles(){
    Map<String, Object> response = new HashMap<>();
    try {
      List<Rol> rolList = this.rolService.findAll();
      response.put("rolList", rolList);
    } catch (Exception e) {
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getRolById(@PathVariable("id") long id){
    Map<String, Object> response = new HashMap<>();
    try {
      Rol rol = this.rolService.findById(id);
      response.put("rol", rol);
    } catch (Exception e) {
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }

  @GetMapping("/{name}")
  public ResponseEntity<?> getRolByName(@PathVariable("name") String rolName){
    Map<String, Object> response = new HashMap<>();
    try {
      Rol rol = this.rolService.findByRolName(rolName);
      response.put("rol", rol);
    } catch (Exception e) {
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<?> createRol(@RequestBody String rolName){
    Map<String, Object> response = new HashMap<>();
    try {
      Rol rol = this.rolService.save(new Rol(rolName));
      response.put("rol", rol);
    } catch (Exception e) {
      response.put("error", e.getMessage());
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }

}
