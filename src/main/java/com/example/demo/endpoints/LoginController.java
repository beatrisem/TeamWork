package com.example.demo.endpoints;

import com.example.demo.data.MySqlDataRepository;
import com.example.demo.data.RestDataRepository;
import com.example.demo.data.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    RestDataRepository repo;

    public LoginController() {
        repo = new MySqlDataRepository();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping({"/api/login", "/api/login/"})
    public User login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        return repo.getUserByUserNameAndPassword(username, password);
    }

}
