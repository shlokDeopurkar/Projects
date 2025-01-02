package com.example.feedback_system.controller;

import com.example.feedback_system.model.User;
import com.example.feedback_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(String username, String password, Model model) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("user", user);
            return "redirect:/feedback";
        }
        model.addAttribute("error", "Invalid username or password.");
        return "login";
    }
}
