package com.vila.controller;

import com.vila.dao.UserDao;
import com.vila.entity.User;
import com.vila.util.DatabaseHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private UserDao dao;

    @BeforeEach
    void setUp() throws Exception {
        Connection conn = DatabaseHelper.getInMemoryConnection();
        DatabaseHelper.initTables(conn);
        dao = new UserDao(conn);
        dao.insert(new User("nazar", "secure123", "user"));
        dao.insert(new User("admin", "admin123",  "admin"));
    }

    @Test
    void testValidLoginReturnsUser() throws Exception {
        User user = dao.findByUsername("nazar");
        assertNotNull(user);
        assertTrue(user.getUserPassword().equals("secure123"));
    }

    @Test
    void testWrongPasswordFails() throws Exception {
        User user = dao.findByUsername("nazar");
        assertNotNull(user);
        assertFalse(user.getUserPassword().equals("wrongpass"));
    }

    @Test
    void testUnknownUserReturnsNull() throws Exception {
        User user = dao.findByUsername("ghost");
        assertNull(user);
    }

    @Test
    void testAdminRoleDetected() throws Exception {
        User user = dao.findByUsername("admin");
        assertNotNull(user);
        assertEquals("admin", user.getUserRole());
    }

    @Test
    void testRegularUserRole() throws Exception {
        User user = dao.findByUsername("nazar");
        assertNotNull(user);
        assertEquals("user", user.getUserRole());
    }

    @Test
    void testLoginAfterPasswordUpdate() throws Exception {
        dao.updatePassword("nazar", "newpass456");
        User user = dao.findByUsername("nazar");
        assertNotNull(user);
        assertTrue(user.getUserPassword().equals("newpass456"));
        assertFalse(user.getUserPassword().equals("secure123"));
    }
}
