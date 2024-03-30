package com.cederj.uff.tcc.stockmaster.controller.user;

import com.cederj.uff.tcc.stockmaster.DTO.user.UpdateUserDTO;
import com.cederj.uff.tcc.stockmaster.VO.user.UserVO;
import com.cederj.uff.tcc.stockmaster.model.user.User;
import com.cederj.uff.tcc.stockmaster.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

  private final UserService userService;

  @Autowired
  private UserController(UserService userService){
    this.userService = userService;
  }

  @GetMapping("/todos")
  public ResponseEntity<List<UserVO>> findAll(){
    List<User> listUsers = this.userService.findAllUsers();
    List<UserVO> listUsersVO = listUsers.stream().map(user -> new UserVO(user.getName(), user.getEmail())).toList();

    return ResponseEntity.ok(listUsersVO);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<Void> updateUserById(@PathVariable("userId") Long id, @RequestBody UpdateUserDTO user) {
    this.userService.updateUserById(id, user);
    return ResponseEntity.ok().build();
  }

}
