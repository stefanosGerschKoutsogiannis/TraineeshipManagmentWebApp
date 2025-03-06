package com.stefanosgersch.traineeship.domain.position_search;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchBasedOnInterest implements PositionSearchStrategy {

    @Override
    public List<TraineeshipPosition> searchPositions(Student student) {
        return List.of();
    }
}
