package com.cederj.uff.tcc.stockmaster.controller.authentication;

import com.cederj.uff.tcc.stockmaster.DTO.authentication.AuthenticationDTO;
import com.cederj.uff.tcc.stockmaster.DTO.authentication.RegisterUserDTO;
import com.cederj.uff.tcc.stockmaster.VO.user.UserResponseVO;
import com.cederj.uff.tcc.stockmaster.infra.security.TokenService;
import com.cederj.uff.tcc.stockmaster.model.user.User;
import com.cederj.uff.tcc.stockmaster.repository.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final UserRepository userRepository;
  private final TokenService tokenService;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService, PasswordEncoder passwordEncoder) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.tokenService = tokenService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/login")
  public ResponseEntity<UserResponseVO> login(@RequestBody @Valid AuthenticationDTO data) {
    User user = this.userRepository.findByUserName(data.userName()).orElseThrow(()->new RuntimeException("User not found"));
    if(passwordEncoder.matches(data.password(), user.getPassword())) {
      String token = this.tokenService.generateToken(user);
      return ResponseEntity.ok(new UserResponseVO(token));
    }
    return ResponseEntity.badRequest().build();
  }
  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody @Valid RegisterUserDTO dados) {
    if(this.userRepository.findByUserName(dados.userName()).isPresent()) return ResponseEntity.badRequest().build();

    String encryptedPassword = new BCryptPasswordEncoder().encode(dados.password());

    User user = new User(dados.name(), dados.userName(), dados.email(), encryptedPassword);
    this.userRepository.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
