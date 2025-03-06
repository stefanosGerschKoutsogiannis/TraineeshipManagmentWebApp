package com.stefanosgersch.traineeship.domain.position_search;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchBasedOnLocation implements PositionSearchStrategy {

    @Override
    public List<TraineeshipPosition> searchPositions(Student student) {
        return List.of();
    }
}
