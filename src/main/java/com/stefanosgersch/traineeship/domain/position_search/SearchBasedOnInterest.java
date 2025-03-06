package com.stefanosgersch.traineeship.domain.position_search;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.StudentRepository;
import com.stefanosgersch.traineeship.repository.TraineeshipPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SearchBasedOnInterest implements PositionSearchStrategy {

    private final TraineeshipPositionRepository traineeshipPositionRepository;
    private final StudentRepository studentRepository;

    @Autowired
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
                .split(","));

        List<TraineeshipPosition> returnedPositions = new ArrayList<>();
        double threshold = 0.5;

        for (TraineeshipPosition position : allPositions) {
            Set<String> topics = Set.of(position.getTopics().split(","));
            if (calculateJaccardSimilarity(studentInterests, topics) > threshold) {
                returnedPositions.add(position);
            }
        }
        return returnedPositions;
    }

    private double calculateJaccardSimilarity(Set<String> interests, Set<String> topics) {
        int intersect = findCommon(interests, topics);
        int union = interests.size() + topics.size();
        return intersect / (double) union;
    }

    private int findCommon(Set<String> interests, Set<String> topics) {
        int counter = 0;

        for (String interest : interests) {
            if (topics.contains(interest)) {
                counter++;
            }
        }
        return counter;
    }
}
