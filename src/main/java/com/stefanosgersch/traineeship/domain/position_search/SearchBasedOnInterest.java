package com.stefanosgersch.traineeship.domain.position_search;

import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.StudentRepository;
import com.stefanosgersch.traineeship.repository.TraineeshipPositionRepository;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.stefanosgersch.traineeship.domain.algorithms.Utilities.calculateJaccardSimilarity;

@Component
public class SearchBasedOnInterest implements PositionSearchStrategy {

    private final TraineeshipPositionRepository traineeshipPositionRepository;
    private final StudentRepository studentRepository;

    public SearchBasedOnInterest(TraineeshipPositionRepository traineeshipPositionRepository,
                                 StudentRepository studentRepository) {
        this.traineeshipPositionRepository = traineeshipPositionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<TraineeshipPosition> searchPositions(String applicantUsername) {
        List<TraineeshipPosition> allPositions = traineeshipPositionRepository.findAll();
        Set<String> studentInterests = Set.of(studentRepository
                .findByUsername(applicantUsername)
                .get()
                .getInterests()
                .toLowerCase()
                .split(",")
        );

        List<TraineeshipPosition> returnedPositions = new ArrayList<>();
        double threshold = 0.5;

        for (TraineeshipPosition position : allPositions) {
            Set<String> topics = Set.of(position.getTopics().toLowerCase().split(","));
            if (calculateJaccardSimilarity(studentInterests, topics) > threshold) {
                returnedPositions.add(position);
            }
        }
        return returnedPositions;
    }
}
