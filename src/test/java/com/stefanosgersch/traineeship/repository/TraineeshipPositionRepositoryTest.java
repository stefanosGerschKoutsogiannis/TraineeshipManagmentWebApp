package com.stefanosgersch.traineeship.repository;

import com.stefanosgersch.traineeship.domain.Company;
import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.domain.Role;
import com.stefanosgersch.traineeship.domain.Student;
import com.stefanosgersch.traineeship.domain.TraineeshipPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class TraineeshipPositionRepositoryTest {

    @Autowired
    private TraineeshipPositionRepository traineeshipPositionRepository;

    @Autowired
    private TestEntityManager entityManager;

    private TraineeshipPosition position1;
    private TraineeshipPosition position2;
    private Student student;
    private Professor professor;
    private Company company;

    @BeforeEach
    void setUp() {
        entityManager.clear(); // Clear persistence context before each test

        // Instantiate related entities directly using their setters
        student = new Student();
        student.setUsername("stud1");
        student.setPassword("pass1");
        student.setRole(Role.STUDENT);
        student.setStudentName("Alice Relational");
        student.setAcademicId("A1");
        student.setAverageGrade(8.0);
        student.setPreferredLocation("CityA");
        student.setInterests("IT");
        student.setSkills("Java");
        student.setLookingForTraineeship(true);
        entityManager.persistAndFlush(student);

        professor = new Professor();
        professor.setUsername("prof1");
        professor.setPassword("pass2");
        professor.setRole(Role.PROFESSOR);
        professor.setProfessorName("Dr. Bob Relational");
        professor.setInterests("AI");
        entityManager.persistAndFlush(professor);

        company = new Company();
        company.setUsername("comp1");
        company.setPassword("pass3");
        company.setRole(Role.COMPANY);
        company.setCompanyName("Tech Corp Relational");
        company.setCompanyLocation("CityB");
        entityManager.persistAndFlush(company);


        entityManager.persistAndFlush(student);
        entityManager.persistAndFlush(professor);
        entityManager.persistAndFlush(company);

        position1 = new TraineeshipPosition();
        position1.setTitle("Software Dev Intern");
        position1.setDescription("Develop backend services.");
        position1.setFromDate(LocalDate.of(2025, 1, 1));
        position1.setToDate(LocalDate.of(2025, 6, 30));
        position1.setTopics("Java, Spring");
        position1.setSkills("Spring Boot, REST");
        position1.setAssigned(false);
        position1.setStudentLogbook(null);
        position1.setPassFailGrade(false);
        position1.setStudent(null);
        position1.setSupervisor(professor);
        position1.setCompany(company);

        position2 = new TraineeshipPosition();
        position2.setTitle("Data Analyst Trainee");
        position2.setDescription("Analyze market data.");
        position2.setFromDate(LocalDate.of(2025, 3, 1));
        position2.setToDate(LocalDate.of(2025, 9, 30));
        position2.setTopics("Statistics, ML");
        position2.setSkills("Python, R");
        position2.setAssigned(true);
        position2.setStudentLogbook("Initial logbook entry.");
        position2.setPassFailGrade(true);
        position2.setStudent(student);
        position2.setSupervisor(professor);
        position2.setCompany(company);

        entityManager.persistAndFlush(position1);
        entityManager.persistAndFlush(position2);
    }

    @Test
    @DisplayName("Should save a new TraineeshipPosition")
    void testSaveTraineeshipPosition() {
        TraineeshipPosition newPosition = new TraineeshipPosition();
        newPosition.setTitle("New Position");
        newPosition.setDescription("New description");
        newPosition.setFromDate(LocalDate.of(2025, 7, 1));
        newPosition.setToDate(LocalDate.of(2025, 12, 31));
        newPosition.setSupervisor(professor);
        newPosition.setCompany(company);

        TraineeshipPosition savedPosition = traineeshipPositionRepository.save(newPosition);

        assertNotNull(savedPosition.getTraineeshipId());
        assertEquals("New Position", savedPosition.getTitle());
        assertEquals(professor.getUsername(), savedPosition.getSupervisor().getUsername());
        assertEquals(company.getCompanyName(), savedPosition.getCompany().getCompanyName());

        Optional<TraineeshipPosition> found = traineeshipPositionRepository.findById(savedPosition.getTraineeshipId());
        assertTrue(found.isPresent());
        assertEquals("New Position", found.get().getTitle());
    }

    @Test
    @DisplayName("Should find TraineeshipPosition by ID when it exists")
    void testFindById_PositionExists() {
        Optional<TraineeshipPosition> foundPositionOptional = traineeshipPositionRepository.findById(position1.getTraineeshipId());

        assertTrue(foundPositionOptional.isPresent());
        TraineeshipPosition foundPosition = foundPositionOptional.get();
        assertEquals(position1.getTitle(), foundPosition.getTitle());
        assertEquals(position1.getDescription(), foundPosition.getDescription());
        assertEquals(position1.getSupervisor().getUsername(), foundPosition.getSupervisor().getUsername());
        assertEquals(position1.getCompany().getCompanyName(), foundPosition.getCompany().getCompanyName());
    }

    @Test
    @DisplayName("Should not find TraineeshipPosition by ID when it does not exist")
    void testFindById_PositionDoesNotExist() {
        Optional<TraineeshipPosition> foundPositionOptional = traineeshipPositionRepository.findById(999L);

        assertFalse(foundPositionOptional.isPresent());
    }

    @Test
    @DisplayName("Should update an existing TraineeshipPosition")
    void testUpdateTraineeshipPosition() {
        Optional<TraineeshipPosition> positionToUpdateOptional = traineeshipPositionRepository.findById(position1.getTraineeshipId());
        assertTrue(positionToUpdateOptional.isPresent());
        TraineeshipPosition positionToUpdate = positionToUpdateOptional.get();

        positionToUpdate.setTitle("Updated Software Dev Position");
        positionToUpdate.setAssigned(true);
        positionToUpdate.setStudent(student);

        TraineeshipPosition updatedPosition = traineeshipPositionRepository.save(positionToUpdate);

        assertEquals("Updated Software Dev Position", updatedPosition.getTitle());
        assertTrue(updatedPosition.isAssigned());
        assertNotNull(updatedPosition.getStudent());
        assertEquals(student.getUsername(), updatedPosition.getStudent().getUsername());

        Optional<TraineeshipPosition> fetchedUpdated = traineeshipPositionRepository.findById(position1.getTraineeshipId());
        assertTrue(fetchedUpdated.isPresent());
        assertEquals("Updated Software Dev Position", fetchedUpdated.get().getTitle());
        assertTrue(fetchedUpdated.get().isAssigned());
        assertEquals(student.getUsername(), fetchedUpdated.get().getStudent().getUsername());
    }

    @Test
    @DisplayName("Should delete a TraineeshipPosition")
    void testDeleteTraineeshipPosition() {
        traineeshipPositionRepository.deleteById(position1.getTraineeshipId());

        Optional<TraineeshipPosition> deletedPosition = traineeshipPositionRepository.findById(position1.getTraineeshipId());
        assertFalse(deletedPosition.isPresent());
    }

    @Test
    @DisplayName("Should find all TraineeshipPositions")
    void testFindAll() {
        Iterable<TraineeshipPosition> allPositions = traineeshipPositionRepository.findAll();
        List<TraineeshipPosition> positionList = new ArrayList<>();
        allPositions.forEach(positionList::add);

        assertEquals(2, positionList.size());
        assertTrue(positionList.stream().anyMatch(p -> p.getTitle().equals("Software Dev Intern")));
        assertTrue(positionList.stream().anyMatch(p -> p.getTitle().equals("Data Analyst Trainee")));
    }
}