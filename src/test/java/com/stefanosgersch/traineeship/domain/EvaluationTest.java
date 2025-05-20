package com.stefanosgersch.traineeship.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvaluationTest {

    private Evaluation evaluation;
    private TraineeshipPosition traineeshipPosition;

    @BeforeEach
    void setUp() {
        evaluation = new Evaluation();

        traineeshipPosition = new TraineeshipPosition();
        traineeshipPosition.setTraineeshipId(301L);
        traineeshipPosition.setTitle("Software Engineering Traineeship");
    }

    @Test
    @DisplayName("Should create Evaluation instance with default constructor")
    void testDefaultConstructor() {
        assertNotNull(evaluation);
        assertNull(evaluation.getEvaluationId());
        assertNull(evaluation.getEvaluationType());
        assertNull(evaluation.getPosition());
        assertEquals(0, evaluation.getMotivation());
        assertEquals(0, evaluation.getEfficiency());
        assertEquals(0, evaluation.getEffectiveness());
    }

    @Test
    @DisplayName("Should create Evaluation instance with all-args constructor")
    void testAllArgsConstructor() {
        Evaluation fullEvaluation = new Evaluation(
                1L,
                EvaluationType.PROFESSOR,
                traineeshipPosition,
                4,
                5,
                4
        );

        assertNotNull(fullEvaluation);
        assertEquals(1L, fullEvaluation.getEvaluationId());
        assertEquals(EvaluationType.PROFESSOR, fullEvaluation.getEvaluationType());
        assertEquals(traineeshipPosition, fullEvaluation.getPosition());
        assertEquals(4, fullEvaluation.getMotivation());
        assertEquals(5, fullEvaluation.getEfficiency());
        assertEquals(4, fullEvaluation.getEffectiveness());
    }

    @Test
    @DisplayName("Should set and get evaluationId correctly")
    void testSetAndGetEvaluationId() {
        Long id = 10L;
        evaluation.setEvaluationId(id);
        assertEquals(id, evaluation.getEvaluationId());
    }

    @Test
    @DisplayName("Should set and get evaluationType correctly")
    void testSetAndGetEvaluationType() {
        evaluation.setEvaluationType(EvaluationType.COMPANY);
        assertEquals(EvaluationType.COMPANY, evaluation.getEvaluationType());
    }

    @Test
    @DisplayName("Should set and get position correctly")
    void testSetAndGetPosition() {
        evaluation.setPosition(traineeshipPosition);
        assertEquals(traineeshipPosition, evaluation.getPosition());

        evaluation.setPosition(null);
        assertNull(evaluation.getPosition());
    }

    @Test
    @DisplayName("Should set and get motivation correctly")
    void testSetAndGetMotivation() {
        int motivation = 3;
        evaluation.setMotivation(motivation);
        assertEquals(motivation, evaluation.getMotivation());
    }

    @Test
    @DisplayName("Should set and get efficiency correctly")
    void testSetAndGetEfficiency() {
        int efficiency = 4;
        evaluation.setEfficiency(efficiency);
        assertEquals(efficiency, evaluation.getEfficiency());
    }

    @Test
    @DisplayName("Should set and get effectiveness correctly")
    void testSetAndGetEffectiveness() {
        int effectiveness = 5;
        evaluation.setEffectiveness(effectiveness);
        assertEquals(effectiveness, evaluation.getEffectiveness());
    }
}