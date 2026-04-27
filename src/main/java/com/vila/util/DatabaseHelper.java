package com.vila.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

    private static final String DB_URL = "jdbc:sqlite:vila.db";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
        }
        return connection;
    }

    public static Connection getInMemoryConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite::memory:");
    }

    public static void initTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("""
            CREATE TABLE IF NOT EXISTS user (
                user_id       INTEGER PRIMARY KEY AUTOINCREMENT,
                user_name     TEXT NOT NULL UNIQUE,
                user_password TEXT NOT NULL,
                user_role     TEXT NOT NULL DEFAULT 'user'
            )
        """);

        stmt.executeUpdate("""
            CREATE TABLE IF NOT EXISTS item (
                item_id          INTEGER PRIMARY KEY AUTOINCREMENT,
                item_name        TEXT NOT NULL,
                item_description TEXT,
                item_price       REAL NOT NULL,
                item_quantity    INTEGER NOT NULL DEFAULT 0,
                item_image_url   TEXT
            )
        """);

        stmt.executeUpdate("""
            CREATE TABLE IF NOT EXISTS cart (
                cart_id    INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id    INTEGER NOT NULL,
                item_id    INTEGER NOT NULL,
                cart_count INTEGER NOT NULL DEFAULT 1,
                FOREIGN KEY (user_id) REFERENCES user(user_id),
                FOREIGN KEY (item_id) REFERENCES item(item_id)
            )
        """);

        stmt.executeUpdate("""
            CREATE TABLE IF NOT EXISTS orders (
                order_id    INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id     INTEGER NOT NULL,
                order_date  TEXT NOT NULL,
                total_price REAL NOT NULL,
                FOREIGN KEY (user_id) REFERENCES user(user_id)
            )
        """);

        stmt.close();
    }
}
