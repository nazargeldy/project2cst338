/**
 * Unit test for the User class.
 *
 * @author Adrian Alonso
 * @version 1.0
 * @since 4/25/26
 */

package com.vila.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Tests")
class UserTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testNewUserConstructor() {
        User user = new User("Adrian123", "123", "admin");

        assertEquals("Adrian123", user.getUserName());
        assertEquals("123", user.getUserPassword());
        assertEquals("admin", user.getUserRole());
    }

    @Test
    void testAlreadyHasAccountConstructor() {
        User user = new User("AdrianHere", "123", "admin", 85);

        assertEquals("AdrianHere", user.getUserName());
        assertEquals("123", user.getUserPassword());
        assertEquals("admin", user.getUserRole());
        assertEquals(85, user.getUserID());
    }

    @Test
    void testBasicMethods() {
        User user = new User("Adrian", "123", "admin", 85);

        String newUserName = "Bob";
        String newPassword = "BobIsCool";
        String newRole = "guest";
        int newID = 12;

        user.setUserName(newUserName);
        user.setUserPassword(newPassword);
        user.setUserRole(newRole);
        user.setUserID(newID);

        assertEquals(newUserName, user.getUserName());
        assertEquals(newPassword, user.getUserPassword());
        assertEquals(newRole, user.getUserRole());
        assertEquals(newID, user.getUserID());
    }
}