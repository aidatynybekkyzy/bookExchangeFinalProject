package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.model.Message;

import java.util.List;

public interface UserService {
    User addUser(User user);
   // Message addAdmin(User user);
   // User getUserById(Long id);
    List<User> getAllUsers();
    Message updateUser(User user);
    Message blockUser(Long id);
    Message unblockUser(Long id);
    User findUserByUsername(String login);
    Message deleteById(long id);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUserById(Long id);
}
