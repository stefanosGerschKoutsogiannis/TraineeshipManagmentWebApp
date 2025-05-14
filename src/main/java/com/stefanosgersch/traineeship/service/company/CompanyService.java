package com.stefanosgersch.traineeship.service.company;

import com.stefanosgersch.traineeship.domain.Company;
import com.stefanosgersch.traineeship.domain.Evaluation;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompanyService {

    Company retrieveCompanyProfile(String username);
    void saveCompanyProfile(Company company);
    List<TraineeshipPosition> retrieveAvailablePositions(String username);
    List<TraineeshipPosition> retrieveAssignedPositions(String username);
    void addPosition(String username, TraineeshipPosition position);
    void evaluateAssignedPosition(Long positionId);
    void saveEvaluation(Long positionId, Evaluation evaluation);
}