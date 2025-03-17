package com.stefanosgersch.traineeship.controller;

import com.stefanosgersch.traineeship.DTO.StudentDTO;
import com.stefanosgersch.traineeship.domain.Role;
import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.service.student.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/dashboard")
    public String getStudentDashboard() {
        return "student/dashboard";
    }

    @RequestMapping("/profile")
    public String retrieveStudentProfile(Model model) {
        // get username from auth
        // add data to the model
        //model.addAllAttributes(studentService.retrieveStudentProfile())
        StudentDTO dummyStudent = new StudentDTO();
        dummyStudent.setStudentName("John Doe");
        dummyStudent.setEmail("john.doe@gmail.com");
        dummyStudent.setAcademicId("123456");
        dummyStudent.setAverageGrade(8.5);
        dummyStudent.setPreferredLocation("Anywhere");
        dummyStudent.setInterests("Programming, Data Science");
        dummyStudent.setSkills("Java, Python, SQL");
        dummyStudent.setLookingForTraineeship(true);
        model.addAttribute("student", dummyStudent);
        return "student/profile";
    }

    @RequestMapping("/save_profile")
    public String saveStudentProfile(@ModelAttribute("studentDTO") StudentDTO studentDTO, Model model) {
        Student student = new Student();
        student.setStudentName(studentDTO.getStudentName());
        student.setAcademicId(studentDTO.getAcademicId());
        student.setAverageGrade(studentDTO.getAverageGrade());
        student.setPreferredLocation(studentDTO.getPreferredLocation());
        student.setInterests(studentDTO.getInterests());
        student.setSkills(studentDTO.getSkills());
        student.setLookingForTraineeship(studentDTO.isLookingForTraineeship());
        student.setEmail(studentDTO.getEmail());
        student.setPassword("lalala");
        student.setRole(Role.STUDENT);
        student.setUsername("username");

        studentService.saveStudentProfile(student);

        return "/student/dashboard";
    }

    @RequestMapping("/logbook")
    public String fillLogbook(Model model) {
        return "/student/logbook";
    }

    @RequestMapping("/save_logbook")
    public String saveLogbook(@ModelAttribute("position")TraineeshipPosition position, Model model) {
        studentService.saveLogbook(position);
        return "redirect:/student/dashboard";
    }
}
