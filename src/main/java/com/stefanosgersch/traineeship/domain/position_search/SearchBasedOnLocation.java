package com.stefanosgersch.traineeship.domain.position_search;

import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.StudentRepository;
import com.stefanosgersch.traineeship.repository.TraineeshipPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchBasedOnLocation implements PositionSearchStrategy {

    private final TraineeshipPositionRepository traineeshipPositionRepository;
    private final StudentRepository studentRepository;

    public SearchBasedOnLocation(TraineeshipPositionRepository traineeshipPositionRepository,
                                 StudentRepository studentRepository) {
        this.traineeshipPositionRepository = traineeshipPositionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<TraineeshipPosition> searchPositions(String applicantUsername) {
        String studentLocation = studentRepository.findByUsername(applicantUsername).get()
                .getPreferredLocation()
                .toLowerCase();
        List<TraineeshipPosition> allPositions = traineeshipPositionRepository.findAll();
        List<TraineeshipPosition> filteredPositions = new ArrayList<>();

        allPositions.forEach(position -> {
            if (!position.isAssigned() && position.getCompany().getCompanyLocation().toLowerCase().equals(studentLocation)) {
                filteredPositions.add(position);
            }
        });
        return filteredPositions;
    }
}
