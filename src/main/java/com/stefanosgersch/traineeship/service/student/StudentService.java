package com.stefanosgersch.traineeship.service.student;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.stereotype.Service;

public interface StudentService {

    Student retrieveStudentProfile(String username);
    void saveStudentProfile(Student student);
    TraineeshipPosition getStudentTraineeshipPosition(String username);
    void saveLogbook(TraineeshipPosition position);
}
