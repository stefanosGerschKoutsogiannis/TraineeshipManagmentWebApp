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

    // US8
    @Override
    public List<TraineeshipPosition> getAllTraineeshipPositions(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Company::getPositions)
                .orElse(Collections.emptyList());
    }

    // US9
    @Override
    public List<TraineeshipPosition> getAssignedTraineeshipPositions(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Company::getPositions)
                .orElse(Collections.emptyList())
                .stream()
                .filter(TraineeshipPosition::isAssigned)
                .toList();
    }

    // US11
    @Override
    public void deleteTraineeshipPosition(Long companyId, TraineeshipPosition position) {
        companyRepository.findById(companyId).ifPresent(company -> {
            company.getPositions().remove(position);
            companyRepository.save(company);
        });
    }
}
