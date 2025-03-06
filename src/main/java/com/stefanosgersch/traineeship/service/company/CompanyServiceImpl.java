package com.stefanosgersch.traineeship.service.company;

import com.stefanosgersch.traineeship.domain.Company;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.CompanyRepository;

import java.util.Collections;
import java.util.List;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<TraineeshipPosition> getAllTraineeshipPositions(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Company::getPositions)
                .orElse(Collections.emptyList());
    }

    @Override
    public List<TraineeshipPosition> getAssignedTraineeshipPositions(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Company::getPositions)
                .orElse(Collections.emptyList())
                .stream()
                .filter(TraineeshipPosition::isAssigned)
                .toList();
    }
}
