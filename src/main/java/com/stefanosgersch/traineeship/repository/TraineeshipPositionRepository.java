package com.stefanosgersch.traineeship.repository;

import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraineeshipPositionRepository extends JpaRepository<TraineeshipPosition, Long> {

}
