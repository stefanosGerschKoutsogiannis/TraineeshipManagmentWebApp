package com.stefanosgersch.traineeship.service.student;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.repository.StudentRepository;

public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void applyForTraineeship(Student student) {
        Student s = studentRepository.findById(student.getStudentId()).orElse(null);
        s.setLookingForTraineeship(true);
    }
}
