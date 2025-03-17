package com.stefanosgersch.traineeship.controller;

import com.stefanosgersch.traineeship.DTO.ProfessorDTO;
import com.stefanosgersch.traineeship.domain.Company;
import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.domain.Role;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.service.professor.ProfessorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @RequestMapping("/dashboard")
    public String getProfessorDashboard() {
        return "professor/dashboard";
    }

    @RequestMapping("/profile")
    public String retrieveProfessorProfile(Model model) {
        // bla bla
        ProfessorDTO dto = new ProfessorDTO();
        model.addAttribute("professorDTO", dto);
        return "professor/profile";
    }

    @RequestMapping("/save_profile")
    public String saveProfessorProfile(@ModelAttribute("professorDTO") ProfessorDTO professorDTO, Model model) {
        // dto to object
        // get username, password, role from session
        Professor professor = new Professor();
        professor.setUsername("sdgag");
        professor.setPassword("ldsga");
        professor.setRole(Role.PROFESSOR);

        professorService.saveProfessorProfile(professor);
        return "professor/dashboard";
    }

    @RequestMapping("/supervised_traineeships")
    public String listSupervisedTraineeships(Model model) {
        // get professor through auth
        //
        //model.addAllAttributes(professorService.getSupervisedPositions(profId))
        return "professor/supervised_traineeships";
    }
}
