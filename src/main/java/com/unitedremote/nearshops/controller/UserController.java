package com.unitedremote.nearshops.controller;

import com.unitedremote.nearshops.model.entity.User;
import com.unitedremote.nearshops.model.repository.UserRepository;
import com.unitedremote.nearshops.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserRepository users;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/sign-in")
    public ResponseEntity signIn(@RequestBody Map<String, String> credentials) {


        try {

            String username = credentials.get("username");
            String password = credentials.get("password");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtTokenProvider.createToken(
                    username, this.users.findByEmail(username)
                            .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"))
                            .getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }


    @PostMapping("/create-user")
    public ResponseEntity createUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = new User();
        user.setEmail(username);
        user.setPassword(this.passwordEncoder.encode(password));
        user.setRoles(Collections.singletonList("ROLE_USER"));
        this.users.save(user);

        return ResponseEntity.accepted().build();


    }


}
