package com.cederj.uff.tcc.stockmaster.controller.user;

import com.cederj.uff.tcc.stockmaster.DTO.authentication.RegisterUserDTO;
import com.cederj.uff.tcc.stockmaster.VO.user.UserVO;
import com.cederj.uff.tcc.stockmaster.model.user.User;
import com.cederj.uff.tcc.stockmaster.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Autowired
  private UserController(UserService userService){
    this.userService = userService;
  }

  @GetMapping("/falar")
  public ResponseEntity<String> digaOlaMundo(){
    return ResponseEntity.ok("Testando o olá mundo");
  }

  @GetMapping
  public ResponseEntity<List<UserVO>> findAll(){
    List<User> listUsers = this.userService.findAllUsers();
    List<UserVO> listUsersVO = listUsers.stream().map(user -> new UserVO(user.getName(), user.getEmail(), user.getPassword())).toList();

    return ResponseEntity.ok(listUsersVO);
  }

}
