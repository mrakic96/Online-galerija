package com.mrakks.onlinegalerija.controller;

import com.mrakks.onlinegalerija.model.User;
import com.mrakks.onlinegalerija.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("usersList", userRepository.findAll());
        return "admin/users";
    }
}
