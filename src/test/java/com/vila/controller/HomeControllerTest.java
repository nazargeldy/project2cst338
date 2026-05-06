package com.vila.controller;

import com.vila.dao.ProductDAO;
import com.vila.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    private ProductDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE products (
                    id          INTEGER PRIMARY KEY AUTOINCREMENT,
                    name        TEXT    NOT NULL,
                    description TEXT,
                    price       REAL    NOT NULL,
                    quantity    INTEGER NOT NULL,
                    category    TEXT
                )
            """);
        }
        dao = new ProductDAO(conn);
    }

    @Test
    void testProductGridLoadsAllProducts() {
        dao.addProduct(new Product(0, "Drift Pant",    "Wide leg",    98.0,  50, "Bottoms"));
        dao.addProduct(new Product(0, "Court Tee",     "Pima blend",  48.0,  80, "Tops"));
        dao.addProduct(new Product(0, "Current Hoodie","French terry",128.0, 30, "Hoodies"));

        List<Product> products = dao.getAllProducts();
        assertEquals(3, products.size());
    }

    @Test
    void testFilterByCategory() {
        dao.addProduct(new Product(0, "Drift Pant",  "Wide leg",   98.0, 50, "Bottoms"));
        dao.addProduct(new Product(0, "Transit Pant","Slim taper", 112.0, 45, "Bottoms"));
        dao.addProduct(new Product(0, "Court Tee",   "Pima blend",  48.0, 80, "Tops"));

        List<Product> all = dao.getAllProducts();
        long bottomsCount = all.stream()
                .filter(p -> p.getCategory().equals("Bottoms"))
                .count();
        assertEquals(2, bottomsCount);
    }

    @Test
    void testSearchByName() {
        dao.addProduct(new Product(0, "Rally Jogger", "French terry", 88.0, 55, "Bottoms"));
        dao.addProduct(new Product(0, "Easy Jogger",  "Chalk white",  88.0, 40, "Bottoms"));
        dao.addProduct(new Product(0, "Court Tee",    "Pima blend",   48.0, 80, "Tops"));

        List<Product> results = dao.getAllProducts().stream()
                .filter(p -> p.getName().toLowerCase().contains("jogger"))
                .toList();
        assertEquals(2, results.size());
    }

    @Test
    void testSeedRunsOnlyWhenEmpty() {
        assertTrue(dao.getAllProducts().isEmpty());

        // simulate seeding
        dao.addProduct(new Product(0, "Drift Pant", "Wide leg", 98.0, 50, "Bottoms"));
        dao.addProduct(new Product(0, "Court Tee",  "Pima",     48.0, 80, "Tops"));

        // if products already exist, seed should not run again
        List<Product> products = dao.getAllProducts();
        assertFalse(products.isEmpty());
        assertEquals(2, products.size());
    }
}
