package com.stefanosgersch.traineeship.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorTest {

    private Professor professor;
    private TraineeshipPosition position1;
    private TraineeshipPosition position2;

    @BeforeEach
    void setUp() {
        professor = new Professor();

        position1 = new TraineeshipPosition();
        position1.setTraineeshipId(1L);
        position1.setTitle("Software Development Intern");

        position2 = new TraineeshipPosition();
        position2.setTraineeshipId(2L);
        position2.setTitle("Data Science Research Assistant");
    }

    @Test
    @DisplayName("Should create Professor instance with default constructor")
    void testDefaultConstructor() {
        assertNotNull(professor);
        assertNull(professor.getProfessorName());
        assertNull(professor.getInterests());
        assertNull(professor.getSupervisedPositions());
        assertNull(professor.getUsername());
        assertNull(professor.getPassword());
        assertNull(professor.getRole());
    }

    @Test
    @DisplayName("Should create Professor instance with all-args constructor")
    void testAllArgsConstructor() {
        List<TraineeshipPosition> positions = new ArrayList<>(Arrays.asList(position1, position2));
        Professor fullProfessor = new Professor(
                "Dr. Alice Smith",
                "AI, Machine Learning",
                positions
        );

        assertNotNull(fullProfessor);
        assertEquals("Dr. Alice Smith", fullProfessor.getProfessorName());
        assertEquals("AI, Machine Learning", fullProfessor.getInterests());
        assertEquals(2, fullProfessor.getSupervisedPositions().size());
        assertTrue(fullProfessor.getSupervisedPositions().contains(position1));
        assertTrue(fullProfessor.getSupervisedPositions().contains(position2));
    }

    @Test
    @DisplayName("Should set and get professorName correctly")
    void testSetAndGetProfessorName() {
        String name = "Dr. John Doe";
        professor.setProfessorName(name);
        assertEquals(name, professor.getProfessorName());
    }

    @Test
    @DisplayName("Should set and get interests correctly")
    void testSetAndGetInterests() {
        String interests = "Software Engineering, Distributed Systems";
        professor.setInterests(interests);
        assertEquals(interests, professor.getInterests());
    }

    @Test
    @DisplayName("Should set and get supervisedPositions correctly")
    void testSetAndGetSupervisedPositions() {
        List<TraineeshipPosition> positions = new ArrayList<>();
        positions.add(position1);
        positions.add(position2);

        professor.setSupervisedPositions(positions);
        assertNotNull(professor.getSupervisedPositions());
        assertEquals(2, professor.getSupervisedPositions().size());
        assertTrue(professor.getSupervisedPositions().contains(position1));
        assertTrue(professor.getSupervisedPositions().contains(position2));

        // Test setting an empty list
        professor.setSupervisedPositions(new ArrayList<>());
        assertTrue(professor.getSupervisedPositions().isEmpty());
    }

    @Test
    @DisplayName("Should set and get inherited User fields correctly")
    void testInheritedUserFields() {
        String username = "johndoe_prof";
        String password = "securePassword123";
        Role role = Role.STUDENT;

        professor.setUsername(username);
        professor.setPassword(password);
        professor.setRole(role);

        assertEquals(username, professor.getUsername());
        assertEquals(password, professor.getPassword());
        assertEquals(role, professor.getRole());
    }

    @Test
    @DisplayName("Should correctly manage supervised positions list")
    void testAddSupervisedPosition() {
        // Ensure the list is initialized if it's null
        professor.setSupervisedPositions(new ArrayList<>());

        professor.getSupervisedPositions().add(position1);
        assertEquals(1, professor.getSupervisedPositions().size());
        assertTrue(professor.getSupervisedPositions().contains(position1));

        professor.getSupervisedPositions().add(position2);
        assertEquals(2, professor.getSupervisedPositions().size());
        assertTrue(professor.getSupervisedPositions().contains(position2));
    }
}