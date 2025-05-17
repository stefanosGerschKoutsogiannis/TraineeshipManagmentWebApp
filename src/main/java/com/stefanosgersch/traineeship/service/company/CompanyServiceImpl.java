package com.stefanosgersch.traineeship.service.company;

import com.stefanosgersch.traineeship.domain.Company;
import com.stefanosgersch.traineeship.domain.Evaluation;
import com.stefanosgersch.traineeship.domain.EvaluationType;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.CompanyRepository;
import com.stefanosgersch.traineeship.repository.TraineeshipPositionRepository;
import com.stefanosgersch.traineeship.service.auth.AuthService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final AuthService authService;

    public CompanyServiceImpl(CompanyRepository companyRepository,
                              AuthService authService) {
        this.companyRepository = companyRepository;
        this.authService = authService;
    }

    @Override
    public Company retrieveCompanyProfile(String username) {
        return companyRepository.findByUsername(username).get();
    }

    // change like save student profile, because model returns null fields for password, etc
    // might change the model
    @Override
    public void saveCompanyProfile(Company company) {
        Company savedCompany = retrieveCompanyProfile(authService.authenticateUser());
        savedCompany.setCompanyName(company.getCompanyName());
        savedCompany.setCompanyLocation(company.getCompanyLocation());
        companyRepository.save(savedCompany);
    }

    @Override
    public List<TraineeshipPosition> retrieveAvailablePositions(String username) {
        List<TraineeshipPosition> positions = companyRepository.findByUsername(username)
                .map(Company::getPositions)
                .orElse(Collections.emptyList())
                .stream()
                .filter(position -> !position.isAssigned())
                .collect(Collectors.toList());
        return positions;
    }

    @Override
    public List<TraineeshipPosition> retrieveAssignedPositions(String username) {
        return companyRepository.findByUsername(username)
                .map(Company::getPositions)
                .orElse(Collections.emptyList())
                .stream()
                .filter(position -> position.isAssigned())
                .toList();
    }

    // problem storing Traineeship at database
    @Override
    public void addPosition(String username, TraineeshipPosition position) {
        Company company = retrieveCompanyProfile(username);
        position.setCompany(company);   // set the position's company to company
        company.getPositions().add(position);   // add the position
        companyRepository.save(company);
    }

    @Override
    public void evaluateAssignedPosition(Long positionId) {
        Company company = retrieveCompanyProfile(authService.authenticateUser());
        TraineeshipPosition traineeshipPosition = (TraineeshipPosition) company.getPositions()
                .stream()
                .filter(position -> position.getTraineeshipId().equals(positionId));
        Evaluation evaluation = new Evaluation();
        evaluation.setEvaluationType(EvaluationType.COMPANY);
        traineeshipPosition.getEvaluations().add(evaluation);
        companyRepository.save(company);
    }

    @Override
    public void saveEvaluation(Long positionId, Evaluation evaluation) {
        return;
    }


}
