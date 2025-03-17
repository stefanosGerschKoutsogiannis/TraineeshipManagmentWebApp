package com.stefanosgersch.traineeship.service.company;

import com.stefanosgersch.traineeship.domain.Company;
import com.stefanosgersch.traineeship.domain.Evaluation;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompanyService {

    Company retrieveCompanyProfile(Long companyId);
    void saveCompanyProfile(Company company);
    List<TraineeshipPosition> retrieveAvailablePositions(Long companyId);
    List<TraineeshipPosition> retrieveAssignedPositions(Long companyId);
    void addPosition(Long companyId, TraineeshipPosition position);
    void deletePosition(Long companyId, TraineeshipPosition position);

    void evaluateAssignedPosition(Long positionId);
    void saveEvaluation(Long positionId, Evaluation evaluation);
}