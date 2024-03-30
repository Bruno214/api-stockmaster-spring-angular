package com.cederj.uff.tcc.stockmaster.service.user;

import com.cederj.uff.tcc.stockmaster.DTO.user.UpdateUserDTO;
import com.cederj.uff.tcc.stockmaster.model.user.User;
import com.cederj.uff.tcc.stockmaster.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

  public void updateUserById(Long id, UpdateUserDTO userDto) {
    Optional<User> optionalUser = this.userRepository.findById(id);

    if (optionalUser.isEmpty()) {
      throw new RuntimeException("User not found for id = " + id);
    }

    String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = optionalUser.get();

    // validar se o user name que eu passei no token é o mesmo do id
    if (!user.getUsername().equals(authenticatedUserName)) {
      throw new RuntimeException("Token invalid for this user");

    }

    user.setName(userDto.name());
    user.setPassword(new BCryptPasswordEncoder().encode(userDto.password()));
    this.userRepository.save(user);
  }
}