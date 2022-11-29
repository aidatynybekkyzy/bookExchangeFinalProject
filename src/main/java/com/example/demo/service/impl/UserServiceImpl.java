package com.example.demo.service.impl;

import com.example.demo.data.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.entity.Role;
import com.example.demo.exceptions.UserAlreadyExists;
import com.example.demo.model.Message;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Override
    public Message addUser(User toSaveUser) {
        if (userRepository.existsByEmail(toSaveUser.getEmail())) {
            throw new UserAlreadyExists("User with this email already exists!");
        } else {
            User user = userRepository.save(toSaveUser);
            user.setRole(Role.USER);

        }
        return new Message("User created");
    }

    @Override
    public Message addAdmin(User toSaveUser) {
        if (userRepository.existsByEmail(toSaveUser.getEmail())) {
            throw new UserAlreadyExists("User with this email already exists!");
        } else {
            User user = userRepository.save(toSaveUser);
            user.setRole(Role.ADMIN);
        }
        return new Message("Admin added");
    }

    @Override
    public User getUserById(Long id) {
        for (User u : userRepository.findAll()) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Message updateUser(User user) {
        for (User u : userRepository.findAll()) {
            if (u.getId().equals(user.getId())) {
                u.setLogin(user.getLogin());
                u.setName(user.getName());
                u.setLocation(user.getLocation());
                u.setEmail(user.getEmail());
                u.setPassword(user.getPassword());
                u.setRole(user.getRole());
                u.setDateOfCreation(user.getDateOfCreation());
                u.setActive(user.getActive());
                userRepository.save(u);
                return new Message("User profile is updated");
            }
        }
        return new Message("User with id" + user.getId() + " does not exists");
    }

    @Override
    public Message blockUser(Long id) {
        for (User u : userRepository.findAll()) {
            if (u.getId().equals(id)) {
                u.setActive(false);
                return new Message("User account is blocked");
            }
        }
        return new Message("User with id " + id + " does not exist");
    }

    @Override
    public Message unblockUser(Long id) {
        for (User u : userRepository.findAll()) {
            if (u.getId().equals(id)) {
                u.setActive(true);
                return new Message("User account is restored");
            }
        }
        return new Message("User with id " + id + " does not exist");
    }

    @Override
    public User findUserByLogin(String login) {
        for (User u : userRepository.findAll()) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                }).orElse(false);
    }

    public static   Predicate<User> isUserExist(User userComing) {
        return user -> userComing.getName().equals(userComing.getName());
    }


}
