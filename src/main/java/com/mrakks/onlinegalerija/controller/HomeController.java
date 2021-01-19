package com.mrakks.onlinegalerija.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //login
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
