package com.stefanosgersch.traineeship.repository;

import com.stefanosgersch.traineeship.domain.Professor;
import com.stefanosgersch.traineeship.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ProfessorRepositoryTest {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Professor professor1;
    private Professor professor2;

    @BeforeEach
    void setUp() {
        entityManager.clear();

        professor1 = new Professor();
        professor1.setUsername("dr.alice");
        professor1.setPassword("pass1");
        professor1.setRole(Role.PROFESSOR);
        professor1.setProfessorName("Dr. Alice Wonderland");
        professor1.setInterests("AI, Robotics");

        professor2 = new Professor();
        professor2.setUsername("prof.bob");
        professor2.setPassword("pass2");
        professor2.setRole(Role.PROFESSOR);
        professor2.setProfessorName("Prof. Bob Builder");
        professor2.setInterests("Software Engineering, Databases");

        System.out.println(professor1.getUsername() +" "+ professor1.getPassword() + " " + professor1.getRole());

        entityManager.persistAndFlush(professor1);
        entityManager.persistAndFlush(professor2);
    }

    @Test
    @DisplayName("Should find professor by username when professor exists")
    void testFindByUsername_ProfessorExists() {
        Optional<Professor> foundProfessorOptional = professorRepository.findByUsername("dr.alice");

        assertTrue(foundProfessorOptional.isPresent());
        Professor foundProfessor = foundProfessorOptional.get();
        assertEquals("dr.alice", foundProfessor.getUsername());
        assertEquals("Dr. Alice Wonderland", foundProfessor.getProfessorName());
        assertEquals("AI, Robotics", foundProfessor.getInterests());
        assertEquals(Role.PROFESSOR, foundProfessor.getRole());
    }

    @Test
    @DisplayName("Should not find professor by username when professor does not exist")
    void testFindByUsername_ProfessorDoesNotExist() {
        Optional<Professor> foundProfessorOptional = professorRepository.findByUsername("nonexistent.prof");

        assertFalse(foundProfessorOptional.isPresent());
    }

    @Test
    @DisplayName("Should save a new Professor entity")
    void testSaveProfessor() {
        Professor newProfessor = new Professor();
        newProfessor.setUsername("new.prof");
        newProfessor.setPassword("newPass");
        newProfessor.setRole(Role.PROFESSOR);
        newProfessor.setProfessorName("Dr. New Guy");
        newProfessor.setInterests("Quantum Physics");

        Professor savedProfessor = professorRepository.save(newProfessor);

        assertNotNull(savedProfessor.getUsername()); // Username is the ID
        assertEquals("new.prof", savedProfessor.getUsername());
        assertEquals("Dr. New Guy", savedProfessor.getProfessorName());

        Optional<Professor> found = professorRepository.findByUsername(savedProfessor.getUsername());
        assertTrue(found.isPresent());
        assertEquals("new.prof", found.get().getUsername());
    }

    @Test
    @DisplayName("Should update an existing Professor entity")
    void testUpdateProfessor() {
        Optional<Professor> professorToUpdateOptional = professorRepository.findByUsername(professor1.getUsername());
        assertTrue(professorToUpdateOptional.isPresent());
        Professor professorToUpdate = professorToUpdateOptional.get();

        professorToUpdate.setProfessorName("Dr. Alice W. Updated");
        professorToUpdate.setInterests("AI, Machine Learning, Robotics");

        Professor updatedProfessor = professorRepository.save(professorToUpdate);

        assertEquals("Dr. Alice W. Updated", updatedProfessor.getProfessorName());
        assertEquals("AI, Machine Learning, Robotics", updatedProfessor.getInterests());

        Optional<Professor> fetchedUpdated = professorRepository.findByUsername(professor1.getUsername());
        assertTrue(fetchedUpdated.isPresent());
        assertEquals("Dr. Alice W. Updated", fetchedUpdated.get().getProfessorName());
    }

    @Test
    @DisplayName("Should find all Professor entities")
    void testFindAll() {
        List<Professor> allProfessors = professorRepository.findAll();

        assertEquals(2, allProfessors.size());
        assertTrue(allProfessors.stream().anyMatch(p -> p.getUsername().equals("dr.alice")));
        assertTrue(allProfessors.stream().anyMatch(p -> p.getUsername().equals("prof.bob")));
    }
}