package com.mrakks.onlinegalerija.controller;

import com.mrakks.onlinegalerija.model.User;
import com.mrakks.onlinegalerija.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    //login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //register
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/newUser")
    public String newUser(@RequestParam("username") String username,
                          @RequestParam("password") String password) {
        User user = new User(username, passwordEncoder.encode(password), "USER", "");
        userRepository.save(user);
        login();
        return "redirect:/";
    }
}
