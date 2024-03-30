package com.cederj.uff.tcc.stockmaster.service.authorization;

import com.cederj.uff.tcc.stockmaster.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public AuthorizationService(UserRepository userRepository){
    this.userRepository = userRepository;
  }
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.userRepository.findByUserName(username).orElseThrow(() -> new RuntimeException("user not found"));
  }
}
