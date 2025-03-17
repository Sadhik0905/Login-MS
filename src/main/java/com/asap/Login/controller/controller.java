package com.asap.Login.controller;

import com.asap.Login.domain.Users;
import com.asap.Login.service.JwtService;
import com.asap.Login.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class controller {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
//    @Autowired
//    private JwtService jwtService;
//
//    @GetMapping("csrf-token")
//    public CsrfToken getCsrfToken(HttpServletRequest request) {
//        return (CsrfToken) request.getAttribute("_csrf");
//    }

    @PostMapping("/register")
    public boolean signUp(@RequestBody Users user){
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        userService.registerUser(user);
        return true;
    }
//
//    @GetMapping("/allUsers")
//    public List<Users> getAllUsers(){
//        return userService.getAllUsers();
//    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getEmail());

        return "Login Failed";

    }
}
