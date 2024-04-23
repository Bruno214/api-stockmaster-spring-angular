package com.cederj.uff.tcc.stockmaster.repositories.user;

import com.cederj.uff.tcc.stockmaster.models.user.User;
import com.cederj.uff.tcc.stockmaster.repositories.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericRepository<User> {
  Optional<User> findByUserName(String userName);
  Optional<User> findByEmail(String email);
}