package com.cederj.uff.tcc.stockmaster.services.user;

import com.cederj.uff.tcc.stockmaster.dtos.user.UpdateUserDto;
import com.cederj.uff.tcc.stockmaster.models.user.User;
import com.cederj.uff.tcc.stockmaster.services.GenericService;


public interface UserService extends GenericService<User> {
   void updateUserById(Long id, UpdateUserDto userDto);
   User findByUserName(String userName);
   User findByEmail(String email);

}