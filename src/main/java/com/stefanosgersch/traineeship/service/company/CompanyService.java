package com.stefanosgersch.traineeship.service.company;

import com.stefanosgersch.traineeship.domain.Company;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    List<TraineeshipPosition> getAllTraineeshipPositions(Long companyId);
    List<TraineeshipPosition> getAssignedTraineeshipPositions(Long companyId);
}
