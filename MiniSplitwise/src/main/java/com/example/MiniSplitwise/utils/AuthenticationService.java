package com.example.MiniSplitwise.utils;

import com.example.MiniSplitwise.config.JwtService;
import com.example.MiniSplitwise.dto.AuthenticationRequest;
import com.example.MiniSplitwise.dto.AuthenticationResponse;
import com.example.MiniSplitwise.dto.RegisterRequest;
import com.example.MiniSplitwise.model.User;
import com.example.MiniSplitwise.repository.UserRepository;
import com.example.MiniSplitwise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(RegisterRequest request) {
        // for(Role r:request.getRoles()) {
        //     System.out.println(r.getRoleName());
        // }
        // byte[] decodedBytes = Base64.getDecoder().decode(request.getAccPassword());
        // String decodedPassword = new String(decodedBytes);
        // System.out.println(decodedPassword);
        System.out.println(request.getContact());
        var user = User.builder()
        .name(request.getName())
        .contact(request.getContact())
        .personalEmail(request.getPersonalEmail())
        .accPassword(passwordEncoder.encode(request.getAccPassword())).contact(request.getContact()).build();
        System.out.println(user);
        // for(Role r:user.getRoles()) {
        //     System.out.println(r.getRoleName());
        // }
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userService.findUserByEmail(request.getEmail());
        HashMap<String, Object> claims = new HashMap<String, Object>();
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}