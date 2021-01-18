package com.mrakks.onlinegalerija.repository;

import com.mrakks.onlinegalerija.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;

    public DbInit(UserRepository userRepository) {
       this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        //Create users
//        User admin = new User("admin", "password123", "ADMIN", "ADD_POST,EDIT_POST");
//        User user = new User("user", "password123", "USER", "");
//
//        List<User> users = Arrays.asList(admin, user);
//
//        // Save to DB
//        this.userRepository.saveAll(users);
    }
}
