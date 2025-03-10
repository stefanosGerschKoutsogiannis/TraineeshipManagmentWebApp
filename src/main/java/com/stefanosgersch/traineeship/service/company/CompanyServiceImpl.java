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

    @Override
    public Company retrieveCompanyProfile(String username) {
        return companyRepository.findByUsername(username).get();
    }

    @Override
    public void saveCompanyProfile(Company company) {
        companyRepository.save(company);
    }

    @Override
    public List<TraineeshipPosition> retrieveAvailablePositions(String username) {
        return companyRepository.findByUsername(username)
                .map(Company::getPositions)
                .orElse(Collections.emptyList());
    }

    @Override
    public List<TraineeshipPosition> retrieveAssignedPositions(String username) {
        return companyRepository.findByUsername(username)
                .map(Company::getPositions)
                .orElse(Collections.emptyList())
                .stream()
                .filter(TraineeshipPosition::isAssigned)
                .toList();
    }

    @Override
    public void addPosition(String username, TraineeshipPosition position) {
        companyRepository.findByUsername(username).ifPresent(company -> {
            company.getPositions().add(position);
        });
    }

    @Override
    public void deletePosition(String username, TraineeshipPosition position) {
        companyRepository.findByUsername(username).ifPresent(company -> {
            company.getPositions().remove(position);
            companyRepository.save(company);
        });
    }

    @Override
    public void evaluateAssignedPosition(Long positionId) {
        return;
    }

    @Override
    public void saveEvaluation(Long positionId, Evaluation evaluation) {
        return;
    }


}
