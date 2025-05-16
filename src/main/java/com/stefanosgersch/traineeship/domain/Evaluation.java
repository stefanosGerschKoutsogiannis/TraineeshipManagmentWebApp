package com.stefanosgersch.traineeship.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationId;

    @Enumerated(EnumType.STRING)
    private EvaluationType evaluationType;

    @ManyToOne
    @JoinColumn(name = "traineeship_id")
    private TraineeshipPosition position;

    private int motivation;
    private int efficiency;
    private int effectiveness;
}
