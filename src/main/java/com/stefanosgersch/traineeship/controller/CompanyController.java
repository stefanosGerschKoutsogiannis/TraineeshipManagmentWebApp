package com.stefanosgersch.traineeship.controller;

import com.stefanosgersch.traineeship.domain.Company;
import com.stefanosgersch.traineeship.domain.Evaluation;
import com.stefanosgersch.traineeship.domain.EvaluationType;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.service.auth.AuthService;
import com.stefanosgersch.traineeship.service.company.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/save_profile")
    public String saveCompanyProfile(@ModelAttribute("company") Company company) {
        companyService.saveCompanyProfile(company);
        return "company/dashboard";
    }

    @RequestMapping("/available_positions")
    public String listAvailablePositions(Model model) {
        model.addAttribute(
                "available_positions",
                companyService.retrieveAvailablePositions(authService.authenticateUser())
        );
        return "company/available_positions";
    }

    @RequestMapping("/assigned_positions")
    public String listAssignedPositions(Model model) {
        model.addAttribute(
                "assigned_positions",
                companyService.retrieveAssignedPositions(authService.authenticateUser())
        );
        return "company/assigned_positions";
    }

    @RequestMapping("/add_position")
    public String showPositionForm(Model model) {
        model.addAttribute("position", new TraineeshipPosition());
        return "company/position_form";
    }

    @RequestMapping("/save_position")
    public String savePosition(@ModelAttribute("position") TraineeshipPosition position) {
        companyService.addPosition(authService.authenticateUser(), position);
        return "company/dashboard";
    }

    @RequestMapping("/evaluate_position")
    public String evaluateAssignedPosition(@ModelAttribute("position_id") Long positionId, Model model) {
        TraineeshipPosition position = companyService.evaluateAssignedPosition(positionId);
        if (position == null) {
            model.addAttribute("alreadyEvaluated", true);
            return "company/evaluate_position";
        }
        model.addAttribute("position", position);
        model.addAttribute("evaluation", new Evaluation());
        return "company/evaluate_position";
    }

    @RequestMapping("/save_evaluation")
    public String saveEvaluation(@ModelAttribute("evaluation") Evaluation evaluation, @ModelAttribute("position_id") Long positionId) {
        evaluation.setEvaluationType(EvaluationType.COMPANY);
        companyService.saveEvaluation(positionId, evaluation);
        return "company/dashboard";
    }

    @RequestMapping("/delete_position")
    public String deletePosition(@RequestParam("position_id") Long positionId) {
        companyService.deletePosition(authService.authenticateUser(), positionId);
        return "company/dashboard";
    }

}
