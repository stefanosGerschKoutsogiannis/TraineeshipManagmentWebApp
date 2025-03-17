package com.stefanosgersch.traineeship.controller;

import com.stefanosgersch.traineeship.domain.*;
import com.stefanosgersch.traineeship.service.user.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

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

    // TODO: hide register behind a register as page
    /*
    @RequestMapping("/register")
    public String register(Model model) {
        return "auth/register_as";
    }
     */

    /*
    @RequestMapping("/register_as")
    public string registerAs(@RequestParam("userType") String userType, Model model) {


     */

    @RequestMapping("/register")
    public String register(@RequestParam("userType") String userType, Model model) {
        User user;
        switch (userType) {
            case "CommitteeMember":
                user = new CommitteeMember();
                break;
            case "Professor":
                user = new Professor();
                break;
            case "Company":
                user = new Company();
                break;
            default:
                user = new Student();
        }
        model.addAttribute("user", user);
        return "auth/register";
    }

    @RequestMapping("/save")
    public String registerUser(@ModelAttribute("user") User user, Model model) {

        if (userService.isUserPresent(user)) {
            model.addAttribute("userAlreadyRegisteredMessage", "User has already been registered.");
            return "auth/register";
        }

        userService.saveUser(user);
        model.addAttribute("userRegisteredSuccessfullyMessage", "User registered successfully");
        return "auth/login";
    }
}
