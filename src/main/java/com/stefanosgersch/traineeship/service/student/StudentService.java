package com.stefanosgersch.traineeship.service.student;

import com.stefanosgersch.traineeship.domain.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    Student retrieveStudentProfile(String username);
    void saveStudentProfile(Student student);
    void applyForTraineeship(Long studentId);

}
