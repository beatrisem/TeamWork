package com.example.demo;

import com.example.demo.data.MySqlDataRepository;
import com.example.demo.data.RestDataRepository;
import com.example.demo.data.Restaurant;
import com.example.demo.data.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResources {

    RestDataRepository repo;

    public HomeResources() {
        repo = new MySqlDataRepository();
    }


    @GetMapping("/user")
    public User getUserByName() {
        User user = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            user = repo.getUserByUserName(((UserDetails) principal).getUsername());
        }
        return user;
    }

    @GetMapping("/restaurant/{name}")
    public Restaurant getByUserName(@PathVariable String name) {
        return repo.getByUserName(name);
    }



}

