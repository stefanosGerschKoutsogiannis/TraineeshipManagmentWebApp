package com.stefanosgersch.traineeship.repository;

import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeshipPositionRepository extends JpaRepository<TraineeshipPosition, Long> {
}
