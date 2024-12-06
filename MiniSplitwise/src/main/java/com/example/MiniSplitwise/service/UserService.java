package com.example.MiniSplitwise.service;

import com.example.MiniSplitwise.dto.UserDTO;
import com.example.MiniSplitwise.model.User;
import com.example.MiniSplitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import  java.util.*;

@Service
public class UserService implements UserDetailsService{
    private final UserRepository userRepository;
    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID insertUser(UserDTO userDTO){
        User user = userDTO.getUserFromDto();
        return userRepository.save(user).getUserId();
    }

    public User updateUser(UUID id, User user) {
        user.setUserId(id);
        return userRepository.save(user);
    }
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> getUserById(UUID userId){
        return userRepository.findById(userId);
    }
    public UserDetails loadUserByUsername(String email) {
        User user = findUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getPersonalEmail(), user.getAccPassword(), user.getAuthorities());
    }
    public User findUserByEmail(String email) throws UsernameNotFoundException{
        Optional<User> user = userRepository.findByPersonalEmail(email);
        if(user.isPresent()){
            System.out.println("User is present");
            User u = user.get();
            return u;
        }
        else{
            logger.error("User not found");
            throw new UsernameNotFoundException("User is not found");
        } 
//        return null;
    }
}
