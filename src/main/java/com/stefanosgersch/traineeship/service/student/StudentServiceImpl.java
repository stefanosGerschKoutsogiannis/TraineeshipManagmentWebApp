package com.stefanosgersch.traineeship.service.student;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.StudentRepository;
import com.stefanosgersch.traineeship.repository.TraineeshipPositionRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private TraineeshipPositionRepository traineeshipPositionRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                              TraineeshipPositionRepository traineeshipPositionRepository) {
        this.studentRepository = studentRepository;
        this.traineeshipPositionRepository = traineeshipPositionRepository;
    }

    @Override
    public Student retrieveStudentProfile(Long studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public void saveStudentProfile(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void applyForTraineeship(Long studentId) {
        studentRepository.findById(studentId)
                .ifPresent(student -> {
                    student.setLookingForTraineeship(true);
                    studentRepository.save(student);
                });
    }

    @Override
    public void saveLogbook(TraineeshipPosition position) {
         traineeshipPositionRepository.save(position);
    }

}
