package com.stefanosgersch.traineeship.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Student student;
    private TraineeshipPosition traineeshipPosition;

    @BeforeEach
    void setUp() {
        student = new Student();

        traineeshipPosition = new TraineeshipPosition();
        traineeshipPosition.setTraineeshipId(101L);
        traineeshipPosition.setTitle("Frontend Development Internship");
    }

    @Test
    @DisplayName("Should create Student instance with default constructor")
    void testDefaultConstructor() {

        assertNotNull(student);

        assertNull(student.getStudentName());
        assertNull(student.getAcademicId());
        assertEquals(0.0, student.getAverageGrade());
        assertNull(student.getPreferredLocation());
        assertNull(student.getInterests());
        assertNull(student.getSkills());
        assertFalse(student.isLookingForTraineeship());
        assertNull(student.getAssignedTraineeshipPosition());
        assertNull(student.getUsername());
        assertNull(student.getPassword());
        assertNull(student.getRole());
    }

    /**
     * Test the all-args constructor.
     */
    @Test
    @DisplayName("Should create Student instance with all-args constructor")
    void testAllArgsConstructor() {
        Student fullStudent = new Student(
                "Alice Wonderland",
                "A1234567",
                8.75,
                "London",
                "Web Development, UI/UX",
                "React, JavaScript, Figma",
                true,
                traineeshipPosition
        );

        assertNotNull(fullStudent);
        assertEquals("Alice Wonderland", fullStudent.getStudentName());
        assertEquals("A1234567", fullStudent.getAcademicId());
        assertEquals(8.75, fullStudent.getAverageGrade());
        assertEquals("London", fullStudent.getPreferredLocation());
        assertEquals("Web Development, UI/UX", fullStudent.getInterests());
        assertEquals("React, JavaScript, Figma", fullStudent.getSkills());
        assertTrue(fullStudent.isLookingForTraineeship());
        assertEquals(traineeshipPosition, fullStudent.getAssignedTraineeshipPosition());
    }

    @Test
    @DisplayName("Should set and get studentName correctly")
    void testSetAndGetStudentName() {
        String name = "Bob The Builder";
        student.setStudentName(name);
        assertEquals(name, student.getStudentName());
    }

    @Test
    @DisplayName("Should set and get academicId correctly")
    void testSetAndGetAcademicId() {
        String academicId = "B9876543";
        student.setAcademicId(academicId);
        assertEquals(academicId, student.getAcademicId());
    }

    @Test
    @DisplayName("Should set and get averageGrade correctly")
    void testSetAndGetAverageGrade() {
        double grade = 9.2;
        student.setAverageGrade(grade);
        assertEquals(grade, student.getAverageGrade());
    }

    @Test
    @DisplayName("Should set and get preferredLocation correctly")
    void testSetAndGetPreferredLocation() {
        String location = "Berlin";
        student.setPreferredLocation(location);
        assertEquals(location, student.getPreferredLocation());
    }

    @Test
    @DisplayName("Should set and get interests correctly")
    void testSetAndGetInterests() {
        String interests = "Data Science, AI Ethics";
        student.setInterests(interests);
        assertEquals(interests, student.getInterests());
    }

    @Test
    @DisplayName("Should set and get skills correctly")
    void testSetAndGetSkills() {
        String skills = "Python, SQL, Machine Learning";
        student.setSkills(skills);
        assertEquals(skills, student.getSkills());
    }

    @Test
    @DisplayName("Should set and get lookingForTraineeship correctly")
    void testSetAndGetLookingForTraineeship() {
        student.setLookingForTraineeship(true);
        assertTrue(student.isLookingForTraineeship());

        student.setLookingForTraineeship(false);
        assertFalse(student.isLookingForTraineeship());
    }

    @Test
    @DisplayName("Should set and get assignedTraineeshipPosition correctly")
    void testSetAndGetAssignedTraineeshipPosition() {
        student.setAssignedTraineeshipPosition(traineeshipPosition);
        assertEquals(traineeshipPosition, student.getAssignedTraineeshipPosition());

        student.setAssignedTraineeshipPosition(null);
        assertNull(student.getAssignedTraineeshipPosition());
    }

    @Test
    @DisplayName("Should set and get inherited User fields correctly")
    void testInheritedUserFields() {
        String username = "alice_student";
        String password = "studentPass";
        Role role = Role.STUDENT;

        student.setUsername(username);
        student.setPassword(password);
        student.setRole(role);

        assertEquals(username, student.getUsername());
        assertEquals(password, student.getPassword());
        assertEquals(role, Role.STUDENT);
    }
}