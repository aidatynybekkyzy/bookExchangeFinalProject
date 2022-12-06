package com.example.demo.service;

import com.example.demo.dto.UserCreationDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.Message;

import java.util.List;

public interface UserService {
    Role saveRole(Role role);

    void addRoleToUser(String username, String rolename);

    UserCreationDto addUser(UserCreationDto userDto);

    List<User> getAllUsers();

    Message updateUser(Long id, User user);

    Message deleteById(long id);

    User getUser(String username);

}
