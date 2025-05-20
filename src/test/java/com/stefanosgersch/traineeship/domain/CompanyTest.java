package com.stefanosgersch.traineeship.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    private Company company;
    private TraineeshipPosition position1;
    private TraineeshipPosition position2;

    @BeforeEach
    void setUp() {
        company = new Company();

        position1 = new TraineeshipPosition();
        position1.setTraineeshipId(201L);
        position1.setTitle("Marketing Intern");

        position2 = new TraineeshipPosition();
        position2.setTraineeshipId(202L);
        position2.setTitle("HR Assistant Trainee");
    }

    @Test
    @DisplayName("Should create Company instance with default constructor")
    void testDefaultConstructor() {
        assertNotNull(company);
        assertNull(company.getCompanyName());
        assertNull(company.getCompanyLocation());
        assertNull(company.getPositions());
        assertNull(company.getUsername());
        assertNull(company.getPassword());
        assertNull(company.getRole());
    }

    @Test
    @DisplayName("Should create Company instance with all-args constructor")
    void testAllArgsConstructor() {
        List<TraineeshipPosition> positions = new ArrayList<>(Arrays.asList(position1, position2));
        Company fullCompany = new Company(
                "Tech Solutions Inc.",
                "New York",
                positions
        );

        assertNotNull(fullCompany);
        assertEquals("Tech Solutions Inc.", fullCompany.getCompanyName());
        assertEquals("New York", fullCompany.getCompanyLocation());
        assertEquals(2, fullCompany.getPositions().size());
        assertTrue(fullCompany.getPositions().contains(position1));
        assertTrue(fullCompany.getPositions().contains(position2));
    }

    @Test
    @DisplayName("Should set and get companyName correctly")
    void testSetAndGetCompanyName() {
        String name = "Global Innovations";
        company.setCompanyName(name);
        assertEquals(name, company.getCompanyName());
    }

    @Test
    @DisplayName("Should set and get companyLocation correctly")
    void testSetAndGetCompanyLocation() {
        String location = "San Francisco";
        company.setCompanyLocation(location);
        assertEquals(location, company.getCompanyLocation());
    }

    @Test
    @DisplayName("Should set and get positions correctly")
    void testSetAndGetPositions() {
        List<TraineeshipPosition> positions = new ArrayList<>();
        positions.add(position1);
        positions.add(position2);

        company.setPositions(positions);
        assertNotNull(company.getPositions());
        assertEquals(2, company.getPositions().size());
        assertTrue(company.getPositions().contains(position1));
        assertTrue(company.getPositions().contains(position2));

        company.setPositions(new ArrayList<>());
        assertTrue(company.getPositions().isEmpty());
    }

    @Test
    @DisplayName("Should set and get inherited User fields correctly")
    void testInheritedUserFields() {
        String username = "tech_company";
        String password = "companyPass";
        Role role = Role.COMPANY;

        company.setUsername(username);
        company.setPassword(password);
        company.setRole(role);

        assertEquals(username, company.getUsername());
        assertEquals(password, company.getPassword());
        assertEquals(role, Role.COMPANY);
    }

    @Test
    @DisplayName("Should correctly manage positions list")
    void testAddPosition() {
        company.setPositions(new ArrayList<>());

        company.getPositions().add(position1);
        assertEquals(1, company.getPositions().size());
        assertTrue(company.getPositions().contains(position1));

        company.getPositions().add(position2);
        assertEquals(2, company.getPositions().size());
        assertTrue(company.getPositions().contains(position2));
    }
}