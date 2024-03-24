package com.cederj.uff.tcc.stockmaster.repository.user;

import com.cederj.uff.tcc.stockmaster.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{
  UserDetails findByUserName(String userName);
}