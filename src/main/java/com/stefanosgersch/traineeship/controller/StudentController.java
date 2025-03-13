package com.stefanosgersch.traineeship.controller;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.service.student.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/student/dashboard")
    public String getStudentDashboard() {
        return "redirect:student/dashboard";
    }

    @RequestMapping("/student/profile")
    public String retrieveStudentProfile(Model model) {
        // get username from auth
        // add data to the model
        //model.addAllAttributes(studentService.retrieveStudentProfile())
        return "redirect:student/profile";
    }

    @RequestMapping("/student/save_profile")
    public String saveStudentProfile(@ModelAttribute("student") Student student, Model model) {
        studentService.saveStudentProfile(student);

        return "redirect:/student/dashboard";
    }

    @RequestMapping("/student/logbook")
    public String fillLogbook(Model model) {
        return null;
    }

    @RequestMapping("/student/save_logbook")
    public String saveLogbook(@ModelAttribute("position")TraineeshipPosition position, Model model) {
        studentService.saveLogbook(position);
        return "redirect:/student/dashboard";
    }
}
