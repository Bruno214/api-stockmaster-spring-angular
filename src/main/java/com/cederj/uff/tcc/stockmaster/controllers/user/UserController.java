package com.cederj.uff.tcc.stockmaster.controllers.user;

import com.cederj.uff.tcc.stockmaster.ServicesImpl.userimpl.UserServiceImpl;
import com.cederj.uff.tcc.stockmaster.dtos.user.UpdateUserDto;
import com.cederj.uff.tcc.stockmaster.vos.user.UserVO;
import com.cederj.uff.tcc.stockmaster.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

  private final UserServiceImpl userServiceImpl;

  @Autowired
  private UserController(UserServiceImpl userServiceImpl){
    this.userServiceImpl = userServiceImpl;
  }

  @GetMapping("/todos")
  public ResponseEntity<List<UserVO>> findAll(){
    List<User> listUsers = this.userServiceImpl.findAll();
    List<UserVO> listUsersVO = listUsers.stream().map(user -> new UserVO(user.getName(), user.getEmail())).toList();

    return ResponseEntity.ok(listUsersVO);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<Void> updateUserById(@PathVariable("userId") Long id, @RequestBody UpdateUserDto user) {
    this.userServiceImpl.updateUserById(id, user);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/username/{userName}")
  public ResponseEntity<UserVO> getUserByUserName(@PathVariable("userName") String userName) {
    User user = this.userServiceImpl.findByUserName(userName);
    UserVO userVO = new UserVO(user.getName(), user.getEmail());
    return ResponseEntity.status(HttpStatus.OK).body(userVO);
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<UserVO> getUserByEmail(@PathVariable("email") String email) {
    User user = this.userServiceImpl.findByEmail(email);
    UserVO userVO = new UserVO(user.getName(), user.getEmail());
    return ResponseEntity.status(HttpStatus.OK).body(userVO);
  }
}
