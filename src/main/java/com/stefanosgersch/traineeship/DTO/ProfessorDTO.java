package com.stefanosgersch.traineeship.DTO;

import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProfessorDTO {

    private String professorName;
    private String email;
    private String interests;
    private List<TraineeshipPosition> supervisedPositions;
}
