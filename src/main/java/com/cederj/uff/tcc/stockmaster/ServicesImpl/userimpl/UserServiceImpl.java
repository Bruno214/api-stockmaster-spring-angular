package com.cederj.uff.tcc.stockmaster.ServicesImpl.userimpl;

import com.cederj.uff.tcc.stockmaster.dtos.user.UpdateUserDto;
import com.cederj.uff.tcc.stockmaster.models.user.User;
import com.cederj.uff.tcc.stockmaster.repositories.GenericRepository;
import com.cederj.uff.tcc.stockmaster.repositories.user.UserRepository;
import com.cederj.uff.tcc.stockmaster.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GenericRepository<User> getRepository() {
        return userRepository;
    }

    @Override
    public void updateUserById(Long id, UpdateUserDto userDto) {
        Optional<User> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User não encontrado para o id = " + id);
        }

        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = optionalUser.get();

        // validar se o user name que eu passei no token é o mesmo do id
        if (!user.getUsername().equals(authenticatedUserName)) {
            throw new RuntimeException("Token inválido para este user");
        }

        user.setName(userDto.name());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.password()));
        this.userRepository.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        Optional<User> optionalUser = this.userRepository.findByUserName(userName.toLowerCase());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User não encontrado com este user name");
        }
        return optionalUser.get();

    }

    @Override
    public User findByEmail(String email) {
        Optional<User> optionalUser = this.userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User não encontrado com este email");
        }
        return optionalUser.get();
    }
}
