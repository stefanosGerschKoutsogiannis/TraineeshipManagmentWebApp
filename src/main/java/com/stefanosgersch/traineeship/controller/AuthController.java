package com.stefanosgersch.traineeship.controller;

import com.stefanosgersch.traineeship.domain.*;
import com.stefanosgersch.traineeship.service.auth.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
public class AuthController {

    private final AuthService userService;

    public AuthController(AuthService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login() {
        return "auth/login";
    }


    @RequestMapping("/register")
    public String register(@RequestParam(value = "userType", required = false) String userType, Model model) {
        if (userType == null) {
            model.addAttribute("userTypeNotSelected", true);
            return "auth/register";
        }

        // remove it
        User user = createUserByType(userType);
        if (user == null) {
            model.addAttribute("errorMessage", "Invalid user type selected.");
            return "auth/register";
        }

        model.addAttribute("user", user);
        model.addAttribute("userType", userType);
        return "auth/register";
    }

    @RequestMapping("/save")
    public String registerUser(@RequestParam("userType") String userType, Model model, @RequestParam Map<String, String> formData) {
        User user = createUserByType(userType);

        if (user == null) {
            model.addAttribute("errorMessage", "Invalid user type.");
            return "auth/register";
        }

        user.setUsername(formData.get("username"));
        user.setPassword(formData.get("password"));

        setUserRole(user);

        if (userService.isUserPresent(user)) {
            // add this with if to register
            model.addAttribute("successMessage", "User has already been registered.");
            return "auth/login";
        }

        userService.saveUser(user);
        model.addAttribute("successMessage", "User registered successfully");
        return "auth/login";
    }

    private User createUserByType(String userType) {
        switch (userType) {
            case "CommitteeMember":
                return new CommitteeMember();
            case "Professor":
                return new Professor();
            case "Company":
                return new Company();
            case "Student":
                return new Student();
            default:
                return null;
        }
    }

    private void setUserRole(User user) {
        if (user instanceof Student) {
            user.setRole(Role.STUDENT);
        } else if (user instanceof CommitteeMember) {
            user.setRole(Role.COMMITTEE_MEMBER);
        } else if (user instanceof Company) {
            user.setRole(Role.COMPANY);
        } else if (user instanceof Professor) {
            user.setRole(Role.PROFESSOR);
        }
    }
}
