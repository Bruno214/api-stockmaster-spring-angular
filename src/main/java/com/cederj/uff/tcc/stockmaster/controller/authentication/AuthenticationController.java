package com.cederj.uff.tcc.stockmaster.controller.authentication;

import com.cederj.uff.tcc.stockmaster.DTO.authentication.AuthenticationDTO;
import com.cederj.uff.tcc.stockmaster.DTO.authentication.RegisterUserDTO;
import com.cederj.uff.tcc.stockmaster.VO.user.UserLoginResponseVO;
import com.cederj.uff.tcc.stockmaster.infra.security.TokenService;
import com.cederj.uff.tcc.stockmaster.model.user.User;
import com.cederj.uff.tcc.stockmaster.repository.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final UserRepository userRepository;
  private final TokenService tokenService;

  public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.tokenService = tokenService;
  }

  @PostMapping("/login")
  public ResponseEntity<UserLoginResponseVO> login(@RequestBody @Valid AuthenticationDTO data) {
    var userNamePassword = new UsernamePasswordAuthenticationToken(data.userName(), data.password());
    var auth = this.authenticationManager.authenticate(userNamePassword);

    var token = this.tokenService.generateToken((User) auth.getPrincipal());

    return ResponseEntity.ok(new UserLoginResponseVO(token));
  }
  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody @Valid RegisterUserDTO dados) {
    if(this.userRepository.findByUserName(dados.userName()) != null) return ResponseEntity.badRequest().build();

    String encryptedPassword = new BCryptPasswordEncoder().encode(dados.password());

    User user = new User(dados.name(), dados.userName(), dados.email(), encryptedPassword);
    this.userRepository.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
