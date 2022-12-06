package com.example.demo.service.impl;

import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.mappers.UserMapper;
import com.example.demo.dto.UserCreationDto;
import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.entity.Message;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User  found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), authorities);

    }

    @Override
    public UserCreationDto addUser(UserCreationDto userDto) {
        if (userRepository.existsUserByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException("User with - %s email already exists " + userDto.getEmail());
        }
        log.info("Saving new user {} to the database", userDto.getUsername());
        UserCreationDto saved = Optional.of(userDto)
                .map(userMapper::userToEntity)
                .map(user -> {
                    user.setName(user.getName());
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    user.setLocation(user.getLocation());
                    user.setContactLink(user.getContactLink());
                    user.setBooks(Collections.emptySet());
                    return userRepository.save(user);
                })
                .map(userMapper::toUserDto)
                .orElseThrow();
        return saved;
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        log.info("Adding role {} to user {}", rolename, username);
        User user = userRepository.findUserByUsername(username);
        Role role = roleRepository.findByName(rolename);
        user.getRoles().add(role);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Fetching all users ");
        return userRepository.findAll();
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findUserByUsername(username);
    }
    //TODO REDO UPDATE METHOD

    @Override
    public Message updateUser(Long userId, User user) {
        User toUpdateUser = userRepository.findById(userId).get();
        if (Objects.nonNull(user.getName()) && !"".equalsIgnoreCase(user.getName())) {
            toUpdateUser.setName(user.getName());
        }
        if (Objects.nonNull(user.getUsername()) && !"".equalsIgnoreCase(user.getUsername())) {
            toUpdateUser.setUsername(user.getUsername());
        }
        if (Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword())) {
            toUpdateUser.setPassword(user.getPassword());
        }
        if (Objects.nonNull(user.getLocation()) && !"".equalsIgnoreCase(user.getLocation())) {
            toUpdateUser.setLocation(user.getLocation());
        }
        if (Objects.nonNull(user.getContactLink()) && !"".equalsIgnoreCase(user.getContactLink().toString())) {
            toUpdateUser.setContactLink(user.getContactLink());
        }

        return new Message("Account with id " + user.getId() + " does not exist");
    }

    @Override
    public Message deleteById(long id) {
        userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                }).orElse(false);
        return new Message("User deleted");
    }

}
