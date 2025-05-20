package com.stefanosgersch.traineeship.repository;

import com.stefanosgersch.traineeship.domain.Company;
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
class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Company company1;
    private Company company2;

    @BeforeEach
    void setUp() {
        entityManager.clear();

        company1 = new Company();
        company1.setUsername("comp.alpha");
        company1.setPassword("alpha_pass");
        company1.setRole(Role.COMPANY);
        company1.setCompanyName("Alpha Corp");
        company1.setCompanyLocation("New York");

        company2 = new Company();
        company2.setUsername("comp.beta");
        company2.setPassword("beta_pass");
        company2.setRole(Role.COMPANY);
        company2.setCompanyName("Beta Solutions");
        company2.setCompanyLocation("London");

        entityManager.persistAndFlush(company1);
        entityManager.persistAndFlush(company2);
    }

    @Test
    @DisplayName("Should find company by username when company exists")
    void testFindByUsername_CompanyExists() {
        Optional<Company> foundCompanyOptional = companyRepository.findByUsername("comp.alpha");

        assertTrue(foundCompanyOptional.isPresent());
        Company foundCompany = foundCompanyOptional.get();
        assertEquals("comp.alpha", foundCompany.getUsername());
        assertEquals("Alpha Corp", foundCompany.getCompanyName());
        assertEquals("New York", foundCompany.getCompanyLocation());
        assertEquals(Role.COMPANY, foundCompany.getRole());
    }

    @Test
    @DisplayName("Should not find company by username when company does not exist")
    void testFindByUsername_CompanyDoesNotExist() {
        Optional<Company> foundCompanyOptional = companyRepository.findByUsername("nonexistent.comp");

        assertFalse(foundCompanyOptional.isPresent());
    }

    @Test
    @DisplayName("Should save a new Company entity")
    void testSaveCompany() {
        Company newCompany = new Company();
        newCompany.setUsername("comp.gamma");
        newCompany.setPassword("gamma_pass");
        newCompany.setRole(Role.COMPANY);
        newCompany.setCompanyName("Gamma Industries");
        newCompany.setCompanyLocation("Paris");

        Company savedCompany = companyRepository.save(newCompany);

        assertNotNull(savedCompany.getUsername());
        assertEquals("comp.gamma", savedCompany.getUsername());
        assertEquals("Gamma Industries", savedCompany.getCompanyName());

        Optional<Company> found = companyRepository.findByUsername(savedCompany.getUsername());
        assertTrue(found.isPresent());
        assertEquals("comp.gamma", found.get().getUsername());
    }

    @Test
    @DisplayName("Should update an existing Company entity")
    void testUpdateCompany() {
        Optional<Company> companyToUpdateOptional = companyRepository.findByUsername(company1.getUsername());
        assertTrue(companyToUpdateOptional.isPresent());
        Company companyToUpdate = companyToUpdateOptional.get();

        companyToUpdate.setCompanyName("Alpha Corp Updated");
        companyToUpdate.setCompanyLocation("Berlin");

        Company updatedCompany = companyRepository.save(companyToUpdate);

        assertEquals("Alpha Corp Updated", updatedCompany.getCompanyName());
        assertEquals("Berlin", updatedCompany.getCompanyLocation());

        Optional<Company> fetchedUpdated = companyRepository.findByUsername(company1.getUsername());
        assertTrue(fetchedUpdated.isPresent());
        assertEquals("Alpha Corp Updated", fetchedUpdated.get().getCompanyName());
    }

    @Test
    @DisplayName("Should find all Company entities")
    void testFindAll() {
        List<Company> allCompanies = companyRepository.findAll();

        assertEquals(2, allCompanies.size());
        assertTrue(allCompanies.stream().anyMatch(c -> c.getUsername().equals("comp.alpha")));
        assertTrue(allCompanies.stream().anyMatch(c -> c.getUsername().equals("comp.beta")));
    }
}