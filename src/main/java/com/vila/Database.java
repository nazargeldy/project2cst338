package com.vila;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String DB_URL = "jdbc:sqlite:vila.db";
    private static Connection connection = null;

    // ─── Get Connection (Singleton) ───────────────────────────
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL);
                System.out.println("Connected to SQLite database.");
                createTables();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // ─── Create Tables if They Don't Exist ────────────────────
    private static void createTables() {
        String createProducts = """
                CREATE TABLE IF NOT EXISTS products (
                    id          INTEGER PRIMARY KEY AUTOINCREMENT,
                    name        TEXT    NOT NULL,
                    description TEXT,
                    price       REAL    NOT NULL,
                    quantity    INTEGER NOT NULL,
                    category    TEXT
                );
                """;

        String createUsers = """
                CREATE TABLE IF NOT EXISTS users (
                    id            INTEGER PRIMARY KEY AUTOINCREMENT,
                    username      TEXT    NOT NULL UNIQUE,
                    password_hash TEXT    NOT NULL,
                    is_admin      INTEGER DEFAULT 0
                );
                """;

        String createOrders = """
                CREATE TABLE IF NOT EXISTS orders (
                    id         INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id    INTEGER NOT NULL,
                    created_at TEXT    NOT NULL,
                    total      REAL    NOT NULL,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                );
                """;

        String createOrderItems = """
                CREATE TABLE IF NOT EXISTS order_items (
                    id          INTEGER PRIMARY KEY AUTOINCREMENT,
                    order_id    INTEGER NOT NULL,
                    product_id  INTEGER NOT NULL,
                    size        TEXT,
                    quantity    INTEGER NOT NULL,
                    unit_price  REAL    NOT NULL,
                    FOREIGN KEY (order_id)   REFERENCES orders(id),
                    FOREIGN KEY (product_id) REFERENCES products(id)
                );
                """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createProducts);
            stmt.execute(createUsers);
            stmt.execute(createOrders);
            stmt.execute(createOrderItems);
            System.out.println("Tables created/verified.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ─── Close Connection ─────────────────────────────────────
    public static void close() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}