package com.stefanosgersch.traineeship.repository;

import com.stefanosgersch.traineeship.domain.Student;
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
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        entityManager.clear(); // Clear persistence context before each test

        student1 = new Student();
        student1.setUsername("student.alice");
        student1.setPassword("pass_alice");
        student1.setRole(Role.STUDENT);
        student1.setStudentName("Alice Johnson");
        student1.setAcademicId("A12345");
        student1.setAverageGrade(8.5);
        student1.setPreferredLocation("New York");
        student1.setInterests("Web Development");
        student1.setSkills("Java, Spring, React");
        student1.setLookingForTraineeship(true);

        student2 = new Student();
        student2.setUsername("student.bob");
        student2.setPassword("pass_bob");
        student2.setRole(Role.STUDENT);
        student2.setStudentName("Bob Williams");
        student2.setAcademicId("B67890");
        student2.setAverageGrade(7.9);
        student2.setPreferredLocation("London");
        student2.setInterests("Data Science");
        student2.setSkills("Python, R, SQL");
        student2.setLookingForTraineeship(false);

        entityManager.persistAndFlush(student1);
        entityManager.persistAndFlush(student2);
    }

    @Test
    @DisplayName("Should find student by username when student exists")
    void testFindByUsername_StudentExists() {
        Optional<Student> foundStudentOptional = studentRepository.findByUsername("student.alice");

        assertTrue(foundStudentOptional.isPresent());
        Student foundStudent = foundStudentOptional.get();
        assertEquals("student.alice", foundStudent.getUsername());
        assertEquals("Alice Johnson", foundStudent.getStudentName());
        assertEquals(8.5, foundStudent.getAverageGrade());
        assertEquals(Role.STUDENT, foundStudent.getRole());
    }

    @Test
    @DisplayName("Should not find student by username when student does not exist")
    void testFindByUsername_StudentDoesNotExist() {
        Optional<Student> foundStudentOptional = studentRepository.findByUsername("nonexistent.student");

        assertFalse(foundStudentOptional.isPresent());
    }

    @Test
    @DisplayName("Should save a new Student entity")
    void testSaveStudent() {
        Student newStudent = new Student();
        newStudent.setUsername("student.charlie");
        newStudent.setPassword("pass_charlie");
        newStudent.setRole(Role.STUDENT);
        newStudent.setStudentName("Charlie Brown");
        newStudent.setAcademicId("C11223");
        newStudent.setAverageGrade(9.0);
        newStudent.setPreferredLocation("Paris");
        newStudent.setInterests("Cybersecurity");
        newStudent.setSkills("Networking, Linux");
        newStudent.setLookingForTraineeship(true);

        Student savedStudent = studentRepository.save(newStudent);

        assertNotNull(savedStudent.getUsername()); // Username is the ID
        assertEquals("student.charlie", savedStudent.getUsername());
        assertEquals("Charlie Brown", savedStudent.getStudentName());

        Optional<Student> found = studentRepository.findByUsername(savedStudent.getUsername());
        assertTrue(found.isPresent());
        assertEquals("student.charlie", found.get().getUsername());
    }

    @Test
    @DisplayName("Should update an existing Student entity")
    void testUpdateStudent() {
        Optional<Student> studentToUpdateOptional = studentRepository.findByUsername(student1.getUsername());
        assertTrue(studentToUpdateOptional.isPresent());
        Student studentToUpdate = studentToUpdateOptional.get();

        studentToUpdate.setStudentName("Alice J. Updated");
        studentToUpdate.setAverageGrade(8.8);
        studentToUpdate.setLookingForTraineeship(false);

        Student updatedStudent = studentRepository.save(studentToUpdate);

        assertEquals("Alice J. Updated", updatedStudent.getStudentName());
        assertEquals(8.8, updatedStudent.getAverageGrade());
        assertFalse(updatedStudent.isLookingForTraineeship());

        Optional<Student> fetchedUpdated = studentRepository.findByUsername(student1.getUsername());
        assertTrue(fetchedUpdated.isPresent());
        assertEquals("Alice J. Updated", fetchedUpdated.get().getStudentName());
    }

    @Test
    @DisplayName("Should find all Student entities")
    void testFindAll() {
        List<Student> allStudents = studentRepository.findAll();

        assertEquals(2, allStudents.size());
        assertTrue(allStudents.stream().anyMatch(s -> s.getUsername().equals("student.alice")));
        assertTrue(allStudents.stream().anyMatch(s -> s.getUsername().equals("student.bob")));
    }
}