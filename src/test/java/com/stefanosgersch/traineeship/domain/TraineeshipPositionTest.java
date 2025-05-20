package com.stefanosgersch.traineeship.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TraineeshipPositionTest {

    private TraineeshipPosition traineeshipPosition;
    private Student student;
    private Professor professor;
    private Company company;
    private Evaluation evaluation1;
    private Evaluation evaluation2;

    @BeforeEach
    void setUp() {
        traineeshipPosition = new TraineeshipPosition();

        // Initialize related entities for testing
        student = new Student();
        student.setUsername("student123");
        student.setStudentName("Test Student");

        professor = new Professor();
        professor.setUsername("prof123");
        professor.setProfessorName("Dr. Test Professor");

        company = new Company();
        company.setUsername("comp123");
        company.setCompanyName("Test Company");

        evaluation1 = new Evaluation();
        evaluation1.setEvaluationId(1L);
        evaluation1.setEvaluationType(EvaluationType.COMPANY);

        evaluation2 = new Evaluation();
        evaluation2.setEvaluationId(2L);
        evaluation2.setEvaluationType(EvaluationType.PROFESSOR);
    }

    @Test
    @DisplayName("Should create TraineeshipPosition instance with default constructor")
    void testDefaultConstructor() {
        assertNotNull(traineeshipPosition);
        assertNull(traineeshipPosition.getTraineeshipId());
        assertNull(traineeshipPosition.getTitle());
        assertNull(traineeshipPosition.getDescription());
        assertNull(traineeshipPosition.getFromDate());
        assertNull(traineeshipPosition.getToDate());
        assertNull(traineeshipPosition.getTopics());
        assertNull(traineeshipPosition.getSkills());
        assertFalse(traineeshipPosition.isAssigned());
        assertNull(traineeshipPosition.getStudentLogbook());
        assertFalse(traineeshipPosition.isPassFailGrade());
        assertNull(traineeshipPosition.getStudent());
        assertNull(traineeshipPosition.getSupervisor());
        assertNull(traineeshipPosition.getCompany());
        assertNull(traineeshipPosition.getEvaluations());
    }

    @Test
    @DisplayName("Should create TraineeshipPosition instance with all-args constructor")
    void testAllArgsConstructor() {
        LocalDate fromDate = LocalDate.of(2025, 6, 1);
        LocalDate toDate = LocalDate.of(2025, 8, 31);
        List<Evaluation> evaluations = new ArrayList<>(Arrays.asList(evaluation1, evaluation2));

        TraineeshipPosition fullPosition = new TraineeshipPosition(
                100L,
                "Backend Development",
                "Develop REST APIs for e-commerce platform.",
                fromDate,
                toDate,
                "Microservices, Databases",
                "Java, Spring Boot, SQL",
                true,
                "Student logbook entry here.",
                true,
                student,
                professor,
                company,
                evaluations
        );

        assertNotNull(fullPosition);
        assertEquals(100L, fullPosition.getTraineeshipId());
        assertEquals("Backend Development", fullPosition.getTitle());
        assertEquals("Develop REST APIs for e-commerce platform.", fullPosition.getDescription());
        assertEquals(fromDate, fullPosition.getFromDate());
        assertEquals(toDate, fullPosition.getToDate());
        assertEquals("Microservices, Databases", fullPosition.getTopics());
        assertEquals("Java, Spring Boot, SQL", fullPosition.getSkills());
        assertTrue(fullPosition.isAssigned());
        assertEquals("Student logbook entry here.", fullPosition.getStudentLogbook());
        assertTrue(fullPosition.isPassFailGrade());
        assertEquals(student, fullPosition.getStudent());
        assertEquals(professor, fullPosition.getSupervisor());
        assertEquals(company, fullPosition.getCompany());
        assertEquals(2, fullPosition.getEvaluations().size());
        assertTrue(fullPosition.getEvaluations().contains(evaluation1));
        assertTrue(fullPosition.getEvaluations().contains(evaluation2));
    }

    @Test
    @DisplayName("Should set and get traineeshipId correctly")
    void testSetAndGetTraineeshipId() {
        Long id = 50L;
        traineeshipPosition.setTraineeshipId(id);
        assertEquals(id, traineeshipPosition.getTraineeshipId());
    }

    @Test
    @DisplayName("Should set and get title correctly")
    void testSetAndGetTitle() {
        String title = "Mobile App Development";
        traineeshipPosition.setTitle(title);
        assertEquals(title, traineeshipPosition.getTitle());
    }

    @Test
    @DisplayName("Should set and get description correctly")
    void testSetAndGetDescription() {
        String description = "Design and implement UI for a new mobile application.";
        traineeshipPosition.setDescription(description);
        assertEquals(description, traineeshipPosition.getDescription());
    }

    @Test
    @DisplayName("Should set and get fromDate correctly")
    void testSetAndGetFromDate() {
        LocalDate date = LocalDate.of(2025, 7, 15);
        traineeshipPosition.setFromDate(date);
        assertEquals(date, traineeshipPosition.getFromDate());
    }

    @Test
    @DisplayName("Should set and get toDate correctly")
    void testSetAndGetToDate() {
        LocalDate date = LocalDate.of(2025, 10, 15);
        traineeshipPosition.setToDate(date);
        assertEquals(date, traineeshipPosition.getToDate());
    }

    @Test
    @DisplayName("Should set and get topics correctly")
    void testSetAndGetTopics() {
        String topics = "Android, Kotlin, UI/UX";
        traineeshipPosition.setTopics(topics);
        assertEquals(topics, traineeshipPosition.getTopics());
    }

    @Test
    @DisplayName("Should set and get skills correctly")
    void testSetAndGetSkills() {
        String skills = "Kotlin, XML, Figma";
        traineeshipPosition.setSkills(skills);
        assertEquals(skills, traineeshipPosition.getSkills());
    }

    @Test
    @DisplayName("Should set and get isAssigned correctly")
    void testSetAndGetIsAssigned() {
        traineeshipPosition.setAssigned(true);
        assertTrue(traineeshipPosition.isAssigned());

        traineeshipPosition.setAssigned(false);
        assertFalse(traineeshipPosition.isAssigned());
    }

    @Test
    @DisplayName("Should set and get studentLogbook correctly")
    void testSetAndGetStudentLogbook() {
        String logbook = "Completed phase 1 of the project.";
        traineeshipPosition.setStudentLogbook(logbook);
        assertEquals(logbook, traineeshipPosition.getStudentLogbook());
    }

    @Test
    @DisplayName("Should set and get passFailGrade correctly")
    void testSetAndGetPassFailGrade() {
        traineeshipPosition.setPassFailGrade(true);
        assertTrue(traineeshipPosition.isPassFailGrade());

        traineeshipPosition.setPassFailGrade(false);
        assertFalse(traineeshipPosition.isPassFailGrade());
    }

    @Test
    @DisplayName("Should set and get student correctly")
    void testSetAndGetStudent() {
        traineeshipPosition.setStudent(student);
        assertEquals(student, traineeshipPosition.getStudent());

        traineeshipPosition.setStudent(null);
        assertNull(traineeshipPosition.getStudent());
    }

    @Test
    @DisplayName("Should set and get supervisor correctly")
    void testSetAndGetSupervisor() {
        traineeshipPosition.setSupervisor(professor);
        assertEquals(professor, traineeshipPosition.getSupervisor());

        traineeshipPosition.setSupervisor(null);
        assertNull(traineeshipPosition.getSupervisor());
    }

    @Test
    @DisplayName("Should set and get company correctly")
    void testSetAndGetCompany() {
        traineeshipPosition.setCompany(company);
        assertEquals(company, traineeshipPosition.getCompany());

        traineeshipPosition.setCompany(null);
        assertNull(traineeshipPosition.getCompany());
    }

    @Test
    @DisplayName("Should set and get evaluations correctly")
    void testSetAndGetEvaluations() {
        List<Evaluation> evaluations = new ArrayList<>();
        evaluations.add(evaluation1);
        evaluations.add(evaluation2);

        traineeshipPosition.setEvaluations(evaluations);
        assertNotNull(traineeshipPosition.getEvaluations());
        assertEquals(2, traineeshipPosition.getEvaluations().size());
        assertTrue(traineeshipPosition.getEvaluations().contains(evaluation1));
        assertTrue(traineeshipPosition.getEvaluations().contains(evaluation2));

        traineeshipPosition.setEvaluations(new ArrayList<>());
        assertTrue(traineeshipPosition.getEvaluations().isEmpty());
    }

    @Test
    @DisplayName("Should correctly manage evaluations list")
    void testAddEvaluation() {
        traineeshipPosition.setEvaluations(new ArrayList<>());

        traineeshipPosition.getEvaluations().add(evaluation1);
        assertEquals(1, traineeshipPosition.getEvaluations().size());
        assertTrue(traineeshipPosition.getEvaluations().contains(evaluation1));

        traineeshipPosition.getEvaluations().add(evaluation2);
        assertEquals(2, traineeshipPosition.getEvaluations().size());
        assertTrue(traineeshipPosition.getEvaluations().contains(evaluation2));
    }
}