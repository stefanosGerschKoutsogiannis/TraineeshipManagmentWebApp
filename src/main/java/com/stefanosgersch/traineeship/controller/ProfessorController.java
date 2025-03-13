package com.stefanosgersch.traineeship.controller;

import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.service.professor.ProfessorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @RequestMapping("/professor/dashboard")
    public String getProfessorDashboard() {
        return "professor/dashboard";
    }

    @RequestMapping("/professor/profile")
    public String retrieveProfessorProfile(Model model) {
        // bla bla
        return "professor/profile";
    }

    @RequestMapping("/professor/save")
    public String saveProfessorProfile(@ModelAttribute("professor") Professor professor, Model model) {
        professorService.saveProfessorProfile(professor);
        return "redirect:/professor/dashboard";
    }

    @RequestMapping("/professor/..")
    public String listSupervisedTraineeships(Model model) {
        // get professor through auth
        //
        //model.addAllAttributes(professorService.getSupervisedPositions(profId))
        return "professor/supervised_traineeships";
    }
}
