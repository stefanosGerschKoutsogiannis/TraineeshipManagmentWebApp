package com.stefanosgersch.traineeship.controller;

import com.stefanosgersch.traineeship.domain.User;
import com.stefanosgersch.traineeship.service.user.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login() {
        return "auth/login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        // return an anonymous instance
        model.addAttribute("user", new User() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singleton(new SimpleGrantedAuthority(getRole().name()));
            }
        });
        return "auth/register";
    }

    @RequestMapping("/save")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userService.isUserPresent(user)) {
            model.addAttribute("userAlreadyRegisteredMessage", "User has already been registered.");
            return "auth/register";
        }

        userService.saveUser(user);
        model.addAttribute("userRegisteredSuccessfullyMessage", "User registered successfully");
        return "auth/login";
    }
}
