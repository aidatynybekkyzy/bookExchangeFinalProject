package com.example.demo.service.impl;

import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.domain.User;
import com.example.demo.domain.Role;
import com.example.demo.model.Message;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepo;

    @Override
    public User addUser(User toSaveUser) {
        log.info("Saving new user {} to the database", toSaveUser.getName());
        return userRepository.save(toSaveUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding  role {} to the user {}", roleName, username);
        User user = userRepository.findUserByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
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
    public Message updateUser(User user) {
        for(User u: userRepository.findAll()){
            if(u.getId().equals(user.getId())){
                u.setUsername(user.getUsername());
                u.setPassword(user.getPassword());
                u.setEmail(user.getEmail());
                u.setName(user.getName());
                u.setLocation(user.getLocation());
                userRepository.save(u);
                return new Message("Account is updated");
            }
        }
        return new Message("Account with id "+ user.getId() + " does not exist");
    }

    @Override
    public Message blockUser(Long id) {
        for(User u: userRepository.findAll()){
            if(u.getId().equals(id)){
                u.setIsActive(false);
                return new Message("Account is blocked");
            }
        }
        return new Message("User with id "+ id + " does not exist");
    }


    @Override
    public Message unblockUser(Long id) {
        for (User u : userRepository.findAll()) {
            if (u.getId().equals(id)) {
                u.setIsActive(true);
                return new Message("User account is restored");
            }
        }
        return new Message("User with id " + id + " does not exist");
    }

    @Override
    @Transactional
    public User findUserByUsername(String login) {
        return userRepository.findUserByUsername(login);
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
   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            log.error("User not found in DB");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found in the DB: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }*/
}
