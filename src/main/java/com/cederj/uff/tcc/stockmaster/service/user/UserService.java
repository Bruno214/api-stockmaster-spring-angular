package com.cederj.uff.tcc.stockmaster.service.user;

import com.cederj.uff.tcc.stockmaster.model.user.User;
import com.cederj.uff.tcc.stockmaster.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  private UserService(UserRepository userRepository)  {
    this.userRepository = userRepository;
  }

  public List<User> findAllUsers(){
    return this.userRepository.findAll();
  }

}