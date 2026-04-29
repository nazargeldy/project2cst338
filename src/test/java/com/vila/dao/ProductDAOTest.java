package com.vila.dao;

import com.vila.entity.Product;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductDAOTest {

    private static Connection connection;
    private static ProductDAO productDAO;

    // ─── Setup in-memory SQLite DB before all tests ───────────
    @BeforeAll
    static void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");

        // Create the products table
        Statement stmt = connection.createStatement();
        stmt.execute("""
                CREATE TABLE IF NOT EXISTS products (
                    id          INTEGER PRIMARY KEY AUTOINCREMENT,
                    name        TEXT    NOT NULL,
                    description TEXT,
                    price       REAL    NOT NULL,
                    quantity    INTEGER NOT NULL,
                    category    TEXT
                );
                """);

        productDAO = new ProductDAO(connection);
    }

    // ─── Close connection after all tests ─────────────────────
    @AfterAll
    static void tearDown() throws Exception {
        connection.close();
    }

    // ─── CREATE ───────────────────────────────────────────────
    @Test
    @Order(1)
    void testAddProduct() {
        Product p = new Product(0, "Alo Leggings", "Premium leggings", 98.00, 50, "Bottoms");
        productDAO.addProduct(p);

        List<Product> products = productDAO.getAllProducts();
        assertFalse(products.isEmpty(), "Products list should not be empty after insert");
        assertEquals("Alo Leggings", products.get(0).getName());
    }

    // ─── READ ALL ─────────────────────────────────────────────
    @Test
    @Order(2)
    void testGetAllProducts() {
        List<Product> products = productDAO.getAllProducts();
        assertNotNull(products, "Product list should not be null");
        assertTrue(products.size() >= 1, "Should have at least one product");
    }

    // ─── READ ONE ─────────────────────────────────────────────
    @Test
    @Order(3)
    void testGetProductById() {
        Product p = productDAO.getProductById(1);
        assertNotNull(p, "Product with id 1 should exist");
        assertEquals("Alo Leggings", p.getName());
        assertEquals(98.00, p.getPrice());
    }

    // ─── UPDATE ───────────────────────────────────────────────
    @Test
    @Order(4)
    void testUpdateProduct() {
        Product p = productDAO.getProductById(1);
        assertNotNull(p);

        p.setPrice(110.00);
        p.setQuantity(40);
        productDAO.updateProduct(p);

        Product updated = productDAO.getProductById(1);
        assertEquals(110.00, updated.getPrice(), "Price should be updated");
        assertEquals(40, updated.getQuantity(), "Quantity should be updated");
    }

    // ─── DELETE ───────────────────────────────────────────────
    @Test
    @Order(5)
    void testDeleteProduct() {
        productDAO.deleteProduct(1);
        Product deleted = productDAO.getProductById(1);
        assertNull(deleted, "Product should be null after deletion");
    }
}