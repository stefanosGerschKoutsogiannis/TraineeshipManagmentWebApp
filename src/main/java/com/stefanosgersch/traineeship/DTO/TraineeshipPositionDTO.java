package com.stefanosgersch.traineeship.DTO;

import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TraineeshipPositionDTO {

    private Long id;
    private String title;
    private String companyName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
