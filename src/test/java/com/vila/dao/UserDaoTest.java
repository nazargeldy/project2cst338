package com.vila.dao;

import com.vila.entity.User;
import com.vila.util.DatabaseHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    private UserDao dao;

    @BeforeEach
    void setUp() throws Exception {
        Connection conn = DatabaseHelper.getInMemoryConnection();
        DatabaseHelper.initTables(conn);
        dao = new UserDao(conn);
    }

    @Test
    void testInsert() throws Exception {
        User user = new User("nazar", "pass123", "user");
        boolean result = dao.insert(user);
        assertTrue(result);
    }

    @Test
    void testFindByUsername() throws Exception {
        dao.insert(new User("nazar", "pass123", "user"));
        User found = dao.findByUsername("nazar");
        assertNotNull(found);
        assertEquals("nazar", found.getUserName());
        assertEquals("user", found.getUserRole());
    }

    @Test
    void testUpdatePassword() throws Exception {
        dao.insert(new User("nazar", "oldpass", "user"));
        boolean updated = dao.updatePassword("nazar", "newpass");
        assertTrue(updated);
        assertEquals("newpass", dao.findByUsername("nazar").getUserPassword());
    }

    @Test
    void testDelete() throws Exception {
        dao.insert(new User("nazar", "pass123", "user"));
        boolean deleted = dao.delete("nazar");
        assertTrue(deleted);
        assertNull(dao.findByUsername("nazar"));
    }
}
