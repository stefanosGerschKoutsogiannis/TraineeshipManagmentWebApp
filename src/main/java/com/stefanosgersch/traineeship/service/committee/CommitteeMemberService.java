package com.stefanosgersch.traineeship.service.committee;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommitteeMemberService {

    List<Student> getStudentsThatAppliedForTraineeship();
    void assignPosition(Long positionId, String studentUsername);
    public List<TraineeshipPosition> getTraineeshipPositionsInProgress();
}
