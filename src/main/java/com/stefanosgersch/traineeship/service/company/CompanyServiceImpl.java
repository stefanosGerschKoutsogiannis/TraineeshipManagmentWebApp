package com.stefanosgersch.traineeship.service.company;

import com.stefanosgersch.traineeship.domain.Company;
import com.stefanosgersch.traineeship.domain.Evaluation;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company retrieveCompanyProfile(Long companyId) {
        return companyRepository.findById(companyId).get();
    }

    @Override
    public void saveCompanyProfile(Company company) {
        companyRepository.save(company);
    }

    @Override
    public List<TraineeshipPosition> retrieveAvailablePositions(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Company::getPositions)
                .orElse(Collections.emptyList());
    }

    @Override
    public List<TraineeshipPosition> retrieveAssignedPositions(Long companyId) {
        return companyRepository.findById(companyId)
                .map(Company::getPositions)
                .orElse(Collections.emptyList())
                .stream()
                .filter(TraineeshipPosition::isAssigned)
                .toList();
    }

    @Override
    public void addPosition(Long companyId, TraineeshipPosition position) {
        companyRepository.findById(companyId).ifPresent(company -> {
            company.getPositions().add(position);
        });
    }

    @Override
    public void deletePosition(Long companyId, TraineeshipPosition position) {
        companyRepository.findById(companyId).ifPresent(company -> {
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
