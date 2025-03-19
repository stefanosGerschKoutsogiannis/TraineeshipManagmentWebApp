package com.stefanosgersch.traineeship.controller;

import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.service.professor.ProfessorService;
import com.stefanosgersch.traineeship.service.auth.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;
    private final AuthService userService;

    public ProfessorController(ProfessorService professorService, AuthService userService) {
        this.professorService = professorService;
        this.userService = userService;
    }

    @RequestMapping("/dashboard")
    public String getProfessorDashboard() {
        return "professor/dashboard";
    }

    @RequestMapping("/profile")
    public String retrieveProfessorProfile(Model model) {
        String username = userService.authenticateUser();
        //ProfessorDTO dto = new ProfessorDTO();
        model.addAttribute("professor", professorService.retrieveProfessorProfile(username));
        return "professor/profile";
    }

    @RequestMapping("/save_profile")
    public String saveProfessorProfile(@ModelAttribute("professor") Professor professor, Model model) {
        // dto to object
        // get username, password, role from session

        // DTO
        professorService.saveProfessorProfile(professor);
        return "professor/dashboard";
    }

    @RequestMapping("/supervised_traineeships")
    public String listSupervisedTraineeships(Model model) {
        String username = userService.authenticateUser();
        model.addAllAttributes(professorService.retrieveAssignedPositions(username));
        return "professor/supervised_traineeships";
    }
}
