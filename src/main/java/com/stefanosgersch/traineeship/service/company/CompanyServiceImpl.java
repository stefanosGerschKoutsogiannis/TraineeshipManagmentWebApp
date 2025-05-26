package com.stefanosgersch.traineeship.service.company;

import com.stefanosgersch.traineeship.domain.Company;
import com.stefanosgersch.traineeship.domain.Evaluation;
import com.stefanosgersch.traineeship.domain.EvaluationType;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import com.stefanosgersch.traineeship.repository.CompanyRepository;
import com.stefanosgersch.traineeship.service.auth.AuthService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

    // change like save student profile, because model returns null fields for password, etc.
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
        return companyRepository.findByUsername(username)
                .map(Company::getPositions)
                .orElse(Collections.emptyList())
                .stream()
                .filter(position -> !position.isAssigned())
                .collect(Collectors.toList());
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

    @Override
    public void addPosition(String username, TraineeshipPosition position) {
        Company company = retrieveCompanyProfile(username);
        position.setCompany(company);   // set the position's company to company
        company.getPositions().add(position);   // add the position
        companyRepository.save(company);
    }

    @Override
    public TraineeshipPosition evaluateAssignedPosition(Long positionId) {
        TraineeshipPosition positionToEval = retrieveAssignedPositions(authService.authenticateUser())
                .stream()
                .filter(position -> position.getTraineeshipId().equals(positionId))
                .findFirst().get();
        if (isEvaluated(positionToEval)) {
            return null;
        }
        return positionToEval;
    }

    private boolean isEvaluated(TraineeshipPosition position) {
        Optional<Evaluation> companyEvaluation = position.getEvaluations()
                .stream()
                .filter(evaluation -> evaluation.getEvaluationType().equals(EvaluationType.COMPANY))
                .findFirst();
        return companyEvaluation.isPresent();
    }

    @Override
    public void saveEvaluation(Long positionId, Evaluation evaluation) {
        TraineeshipPosition position = companyRepository.findAll().stream()
                .flatMap(company -> company.getPositions().stream())
                .filter(p -> p.getTraineeshipId().equals(positionId))
                .findFirst()
                .get();
        
        evaluation.setPosition(position);
        position.getEvaluations().add(evaluation);
        companyRepository.save(position.getCompany());
    }

    @Override
    public void deletePosition(String username, Long positionId) {
        Company company = retrieveCompanyProfile(username);
        TraineeshipPosition positionToDelete = company.getPositions().stream()
                .filter(position -> position.getTraineeshipId().equals(positionId))
                .findFirst().get();

        System.out.println(positionToDelete.getTitle());

        // Remove position from company's list
        company.getPositions().remove(positionToDelete);
        positionToDelete.setCompany(null);
        
        // Clear any existing evaluations
        if (positionToDelete.getEvaluations() != null) {
            positionToDelete.getEvaluations().clear();
        }
        
        // Clear any existing student or supervisor references
        positionToDelete.setStudent(null);
        positionToDelete.setSupervisor(null);

        companyRepository.save(company);
    }

}
