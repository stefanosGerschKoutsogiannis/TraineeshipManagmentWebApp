package com.stefanosgersch.traineeship.domain.position_search;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.StudentRepository;
import com.stefanosgersch.traineeship.repository.TraineeshipPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchBasedOnLocation implements PositionSearchStrategy {

    private final TraineeshipPositionRepository traineeshipPositionRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public SearchBasedOnLocation(TraineeshipPositionRepository traineeshipPositionRepository,
                                 StudentRepository studentRepository) {
        this.traineeshipPositionRepository = traineeshipPositionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<TraineeshipPosition> searchPositions(String applicantUsername) {
        return List.of();
    }
}
