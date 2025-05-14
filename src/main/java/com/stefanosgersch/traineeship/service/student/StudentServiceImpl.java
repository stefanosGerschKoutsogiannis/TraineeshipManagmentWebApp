package com.stefanosgersch.traineeship.service.student;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.StudentRepository;
import com.stefanosgersch.traineeship.repository.TraineeshipPositionRepository;
import com.stefanosgersch.traineeship.service.auth.AuthService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final TraineeshipPositionRepository traineeshipPositionRepository;
    private final AuthService authService;

    public StudentServiceImpl(StudentRepository studentRepository,
                              TraineeshipPositionRepository traineeshipPositionRepository,
                              AuthService authService) {
        this.studentRepository = studentRepository;
        this.traineeshipPositionRepository = traineeshipPositionRepository;
        this.authService = authService;
    }

    @Override
    public Student retrieveStudentProfile(String username) {
        return studentRepository.findByUsername(username).get();
    }

    @Override
    public void saveStudentProfile(Student student) {
        Student savedStudent = retrieveStudentProfile(authService.authenticateUser());
        savedStudent.setStudentName(student.getStudentName());
        savedStudent.setAcademicId(student.getAcademicId());
        savedStudent.setLookingForTraineeship(student.isLookingForTraineeship());
        savedStudent.setInterests(student.getInterests());
        savedStudent.setSkills(student.getSkills());
        savedStudent.setAverageGrade(student.getAverageGrade());
        savedStudent.setPreferredLocation(student.getPreferredLocation());

        studentRepository.save(savedStudent);
    }

    @Override
    public TraineeshipPosition getStudentTraineeshipPosition(String username) {
        TraineeshipPosition position = retrieveStudentProfile(username).getAssignedTraineeshipPosition();
        if (position != null) {
            return position;
        }
        return null;
    }

    @Override
    public void saveLogbook(TraineeshipPosition position) {
        TraineeshipPosition saved_position = traineeshipPositionRepository.findById(position.getTraineeshipId()).get();
        saved_position.setStudentLogbook(position.getStudentLogbook());
        traineeshipPositionRepository.save(saved_position);
    }

}
