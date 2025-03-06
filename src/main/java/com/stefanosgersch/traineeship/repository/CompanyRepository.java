package com.stefanosgersch.traineeship.repository;

import com.stefanosgersch.traineeship.domain.Company;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
