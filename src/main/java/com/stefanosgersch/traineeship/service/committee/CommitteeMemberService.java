package com.stefanosgersch.traineeship.service.committee;

import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommitteeMemberService {

    List<Student> retrieveTraineeshipApplicants();
    void assignPosition(Long positionId, String studentUsername);
    List<TraineeshipPosition> getTraineeshipPositionsInProgress();

    void assignSupervisor(Long positionId, String strategy);
    List<TraineeshipPosition> retrievePositionsForApplicant(String applicantUsername, String strategy);
}
