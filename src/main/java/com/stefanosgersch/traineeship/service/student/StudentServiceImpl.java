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
        Student saved_student = retrieveStudentProfile(authService.authenticateUser());
        saved_student.setStudentName(student.getStudentName());
        saved_student.setAcademicId(student.getAcademicId());
        saved_student.setLookingForTraineeship(student.isLookingForTraineeship());
        saved_student.setInterests(student.getInterests());
        saved_student.setSkills(student.getSkills());
        saved_student.setAverageGrade(student.getAverageGrade());
        saved_student.setPreferredLocation(student.getPreferredLocation());

        studentRepository.save(saved_student);
    }

    @Override
    public TraineeshipPosition getStudentTraineeshipPosition(String username) {
        TraineeshipPosition position = retrieveStudentProfile(username).getAssignedTraineeshipPosition();
        if (position != null) {
            return position;
        }
        return null;
    }

    // if no traineeship, throw something else
    @Override
    public void saveLogbook(TraineeshipPosition position) {
        TraineeshipPosition saved_position = traineeshipPositionRepository.findById(position.getTraineeshipId()).get();
        saved_position.setStudentLogbook(position.getStudentLogbook());
        traineeshipPositionRepository.save(saved_position);
    }

}
