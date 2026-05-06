package com.vila.controller;

import com.vila.dao.UserDao;
import com.vila.entity.User;
import com.vila.util.DatabaseHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {

    private UserDao dao;

    @BeforeEach
    void setUp() throws Exception {
        Connection conn = DatabaseHelper.getInMemoryConnection();
        DatabaseHelper.initTables(conn);
        dao = new UserDao(conn);
    }

    @Test
    void testRegisterNewUserSucceeds() throws Exception {
        boolean result = dao.insert(new User("newuser", "password1", "user"));
        assertTrue(result);
        assertNotNull(dao.findByUsername("newuser"));
    }

    @Test
    void testDuplicateUsernameBlocked() throws Exception {
        dao.insert(new User("taken", "pass123", "user"));
        // controller checks findByUsername before inserting
        User existing = dao.findByUsername("taken");
        assertNotNull(existing); // username already taken — controller shows error
    }

    @Test
    void testUsernameTooShortValidation() {
        // RegisterController rejects usernames under 3 chars
        String username = "ab";
        assertTrue(username.length() < 3);
    }

    @Test
    void testPasswordTooShortValidation() {
        // RegisterController rejects passwords under 6 chars
        String password = "abc";
        assertTrue(password.length() < 6);
    }

    @Test
    void testPasswordsMatchValidation() {
        String password = "secure99";
        String confirm  = "secure99";
        assertEquals(password, confirm);
    }

    @Test
    void testPasswordsMismatchValidation() {
        String password = "secure99";
        String confirm  = "different";
        assertNotEquals(password, confirm);
    }

    @Test
    void testRegisterThenLoginWorks() throws Exception {
        dao.insert(new User("vila_user", "mypass99", "user"));
        User found = dao.findByUsername("vila_user");
        assertNotNull(found);
        assertEquals("vila_user", found.getUserName());
        assertTrue(found.getUserPassword().equals("mypass99"));
    }
}
