package com.stefanosgersch.traineeship.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class CommitteeTest {

    private CommitteeMember committeeMember;

    @BeforeEach
    void setUp() {
        committeeMember = new CommitteeMember();
    }

    @Test
    @DisplayName("Should create CommitteeMember instance with default constructor")
    void testDefaultConstructor() {
        assertNotNull(committeeMember);
        assertNull(committeeMember.getUsername());
        assertNull(committeeMember.getPassword());
        assertNull(committeeMember.getRole());
    }

    @Test
    @DisplayName("Should set and get inherited User fields correctly")
    void testInheritedUserFields() {
        String username = "committee_user";
        String password = "securePass";
        Role role = Role.COMMITTEE_MEMBER;

        committeeMember.setUsername(username);
        committeeMember.setPassword(password);
        committeeMember.setRole(role);

        assertEquals(username, committeeMember.getUsername());
        assertEquals(password, committeeMember.getPassword());
        assertEquals(role, committeeMember.getRole());
    }

    @Test
    @DisplayName("Should return correct authorities based on inherited role")
    void testGetAuthorities() {
        committeeMember.setRole(Role.COMMITTEE_MEMBER);
        Collection<? extends GrantedAuthority> authorities = committeeMember.getAuthorities();
        assertNotNull(authorities);
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("COMMITTEE_MEMBER")));
    }
}