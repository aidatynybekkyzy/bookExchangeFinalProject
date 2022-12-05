package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.model.Message;

import java.util.List;

public interface UserService {
    User addUser(User user);
    List<User> getAllUsers();
    Message updateUser(Long id, User user);
    Message deleteById(long id);
    User getUserById(Long id);
    String signUpUser(User user);
    Integer enableUser(String email) ;

}
