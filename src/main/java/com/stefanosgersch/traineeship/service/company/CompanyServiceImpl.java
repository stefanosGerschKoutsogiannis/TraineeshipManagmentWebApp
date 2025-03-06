package com.stefanosgersch.traineeship.service.company;

import com.stefanosgersch.traineeship.domain.Company;
import com.stefanosgersch.traineeship.domain.Evaluation;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.CompanyRepository;

import java.util.Collections;
import java.util.List;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // US7, change it to return only what is needed(create DTOs)
    @Override
    public Company retrieveCompanyProfile(String username) {
        return companyRepository.findByUsername(username).get();
    }

    @Override
    public void saveCompanyProfile(Company company) {
        companyRepository.save(company);
    }

    // US8, or query database
    @Override
    public List<TraineeshipPosition> getAvailablePositions(String username) {
        return companyRepository.findByUsername(username)
                .map(Company::getPositions)
                .orElse(Collections.emptyList());
    }

    // US9
    @Override
    public List<TraineeshipPosition> getAssignedPositions(String username) {
        return companyRepository.findByUsername(username)
                .map(Company::getPositions)
                .orElse(Collections.emptyList())
                .stream()
                .filter(TraineeshipPosition::isAssigned)
                .toList();
    }

    // US10
    @Override
    public void addPosition(String username, TraineeshipPosition position) {
        companyRepository.findByUsername(username).ifPresent(company -> {
            company.getPositions().add(position);
        });
    }

    // US11
    @Override
    public void deletePosition(String username, TraineeshipPosition position) {
        companyRepository.findByUsername(username).ifPresent(company -> {
            company.getPositions().remove(position);
            companyRepository.save(company);
        });
    }

    // US12
    public void evaluateAssignedPosition(Long positionId) {
        return;
    }

    public void saveEvaluation(Long positionId, Evaluation evaluation) {
        return;
    }


}
