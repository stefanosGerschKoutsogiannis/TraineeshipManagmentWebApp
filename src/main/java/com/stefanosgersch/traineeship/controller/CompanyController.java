package com.stefanosgersch.traineeship.controller;

import com.stefanosgersch.traineeship.service.auth.AuthService;
import com.stefanosgersch.traineeship.service.company.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {


    private final CompanyService companyService;
    private final AuthService authService;

    public CompanyController(CompanyService companyService,
                             AuthService authService) {
        this.companyService = companyService;
        this.authService = authService;
    }

    @RequestMapping("/dashboard")
    public String getCompanyDashboard() {
        return "company/dashboard";
    }

    @RequestMapping("/profile")
    public String retrieveCompanyProfile(Model model) {
        String username = authService.authenticateUser();
        model.addAttribute("company", companyService.retrieveCompanyProfile(username));
        return "company/profile";
    }
}
