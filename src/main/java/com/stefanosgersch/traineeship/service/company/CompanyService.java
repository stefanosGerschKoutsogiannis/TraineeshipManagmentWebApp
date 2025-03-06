package com.stefanosgersch.traineeship.service.company;

import com.stefanosgersch.traineeship.domain.Company;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    Company retrieveCompanyProfile(String username);
    void saveCompanyProfile(Company company);
    List<TraineeshipPosition> getAvailablePositions(String username);
    List<TraineeshipPosition> getAssignedPositions(String username);
    void addPosition(String username, TraineeshipPosition position);
    void deletePosition(String username, TraineeshipPosition position);
}