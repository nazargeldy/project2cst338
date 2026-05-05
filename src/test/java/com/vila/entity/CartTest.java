/**
 * Unit test for the Cart class.
 *
 * @author Adrian Alonso
 * @version 1.0
 * @since 4/29/26
 */

package com.vila.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cart Test")
class CartTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testNewCartConstructor() {
        Cart cart = new Cart(1, 1000, 2000);

        assertEquals(1, cart.getCartCount());
        assertEquals(1000, cart.getProductID());
        assertEquals(2000, cart.getUserID());
    }

    @Test
    void testExistingCartConstructor() {
        Cart cart = new Cart(1, 1000, 2000, 3000);

        assertEquals(1, cart.getCartCount());
        assertEquals(1000, cart.getProductID());
        assertEquals(2000, cart.getUserID());
        assertEquals(3000, cart.getCartID());
    }

    @Test
    void testBasicMethods() {
        Cart cart = new Cart(1, 1000, 2000, 3000);

        int newCartCount = 2;
        int newProductID = 10;
        int newUserID = 20;
        int newCartID = 30;

        cart.setCartCount(newCartCount);
        cart.setProductID(newProductID);
        cart.setUserID(newUserID);
        cart.setCartID(newCartID);

        assertEquals(newCartCount, cart.getCartCount());
        assertEquals(newProductID, cart.getProductID());
        assertEquals(newUserID, cart.getUserID());
        assertEquals(newCartID, cart.getCartID());
    }

    @Test
    void testMultipliers() {
        Cart cart = new Cart(0, 1000, 2000, 3000);

        cart.increaseCartCount();
        assertEquals(1, cart.getCartCount());

        cart.decreaseCartCount();
        assertEquals(0, cart.getCartCount());
    }

}