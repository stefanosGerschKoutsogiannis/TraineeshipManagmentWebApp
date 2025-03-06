package com.stefanosgersch.traineeship.service.student;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.repository.StudentRepository;

public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public void applyForTraineeship(Long studentId) {
        studentRepository.findById(studentId)
                .ifPresent(student -> {
                    student.setLookingForTraineeship(true);
                    studentRepository.save(student); //
                });
    }

}
