package com.stefanosgersch.traineeship.controller;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.service.student.StudentService;
import com.stefanosgersch.traineeship.service.auth.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final AuthService userService;

    public StudentController(StudentService studentService, AuthService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @RequestMapping("/dashboard")
    public String getStudentDashboard() {
        return "student/dashboard";
    }

    @RequestMapping("/profile")
    public String retrieveStudentProfile(Model model) {
        String username = userService.authenticateUser();
        model.addAttribute("student", studentService.retrieveStudentProfile(username));
        return "student/profile";
    }

    @RequestMapping("/save_profile")
    public String saveStudentProfile(@ModelAttribute("student") Student student, Model model) {
        studentService.saveStudentProfile(student);
        return "student/dashboard";
    }

    @RequestMapping("/logbook")
    public String fillLogbook(Model model) {
        TraineeshipPosition position = studentService.getStudentTraineeshipPosition(
                userService.authenticateUser());
        if (position == null) {
            model.addAttribute(
                    "noPosition", "No traineeship position found "
            );
        }
        model.addAttribute("position", position);
        return "student/logbook";
    }

    @RequestMapping("/save_logbook")
    public String saveLogbook(@ModelAttribute("position")TraineeshipPosition position, Model model) {
        studentService.saveLogbook(position);
        return "student/dashboard";
    }
}
