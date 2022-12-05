package com.example.demo.service.impl;

import com.example.demo.domain.Book;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenService;
import com.example.demo.repository.UserRepository;
import com.example.demo.domain.User;
import com.example.demo.model.Message;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public User addUser(User toSaveUser) {
        if (userRepository.existsUserByEmail(toSaveUser.getEmail())){
            throw new UserAlreadyExistsException("User with - %s email already exists " + toSaveUser.getEmail());
        }
        log.info("Saving new user {} to the database", toSaveUser.getUsername());
        return userRepository.save(toSaveUser);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Fetching users ");
        return userRepository.findAll();
    }

    @Override
    public Message updateUser(Long userId,User user) {
        User toUpdateUser = userRepository.findById(userId).get();
        if (Objects.nonNull(user.getFirstName()) && !"".equalsIgnoreCase(user.getFirstName())) {
            toUpdateUser.setFirstName(user.getFirstName());
        }
        if (Objects.nonNull(user.getLastName()) && !"".equalsIgnoreCase(user.getLastName())) {
            toUpdateUser.setLastName(user.getLastName());
        }
        if (Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword())) {
            toUpdateUser.setPassword(user.getPassword());
        }
        if (Objects.nonNull(user.getLocation()) && !"".equalsIgnoreCase(user.getLocation())) {
            toUpdateUser.setLocation(user.getLocation());
        }
        if (Objects.nonNull(user.getRole()) && !"".equalsIgnoreCase(user.getRole().toString())) {
            toUpdateUser.setRole(user.getRole());
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, email)));
    }

    @Override
    public Integer enableUser(String email) {
        return userRepository.enableUser(email);
    }

    @Override
    public String signUpUser(User user) {
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();
        if (userExists) {
            //TODO check if attributes are the same and
            //TODO IF email not confirmed send confirmation email
            throw new UserAlreadyExistsException("Email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());
        user.setPassword(encodedPassword);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO SEND EMAIL
        return token;
    }

}
