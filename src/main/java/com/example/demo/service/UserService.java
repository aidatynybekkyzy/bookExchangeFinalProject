package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.Message;

import java.util.List;

public interface UserService {
    Message addUser(User user);
    Message addAdmin(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    Message updateUser(User user);
    Message blockUser(Long id);
    Message unblockUser(Long id);
    User findUserByLogin(String login);
    boolean deleteById(long id);
}
