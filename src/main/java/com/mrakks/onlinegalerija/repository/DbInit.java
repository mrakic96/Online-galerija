package com.mrakks.onlinegalerija.repository;

import com.mrakks.onlinegalerija.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
//        //Delete all
//        this.userRepository.deleteAll();
//        //Create users
//        User admin = new User("admin", passwordEncoder.encode("password123"), "ADMIN", "ADD_POST,EDIT_POST");
//        User user = new User("user", passwordEncoder.encode("password123"), "USER", "");
//
//        List<User> users = Arrays.asList(admin, user);
//
//        // Save to DB
//        this.userRepository.saveAll(users);
    }
}
