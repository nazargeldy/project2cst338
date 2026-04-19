# Vila Activewear

A JavaFX desktop e-commerce application for premium activewear shopping. Clean, minimal interface inspired by brands like Alo — built with JavaFX, Gradle, and SQLite.

## Team

| Name | GitHub | Role |
|------|--------|------|
| Nazar Geldymuradov | @nazargeldy | UI / Scene design |
| Adrian Alonso Perez | | User entity & DAO |
| Diego Medina Rodriguez | | |
| Jovani Avila Ramirez | | |

## Features

- **Login / Registration** — credential validation, new account creation
- **Home / Storefront** — product grid with search, hero banner
- **Product Detail** — size selection, add to bag
- **Shopping Cart** — item list, order summary, checkout

## Tech Stack

- Java 17
- JavaFX 21
- Gradle 8
- SQLite (via `sqlite-jdbc`)
- JUnit 5

## Getting Started

1. Clone the repo
2. Open in IntelliJ IDEA — it will detect the Gradle project automatically
3. Let Gradle sync finish
4. Run `VilaApp` (`src/main/java/com/vila/VilaApp.java`)

Requires Java 17+ and IntelliJ IDEA with the JavaFX plugin.

## Project Structure

```
src/
  main/
    java/com/vila/
      VilaApp.java           # Application entry point
      SceneFactory.java      # Handles all scene transitions
      controller/
        LoginController.java
        HomeController.java
        ProductDetailController.java
        CartController.java
    resources/com/vila/
      login.fxml
      home.fxml
      product-detail.fxml
      cart.fxml
      styles.css
  test/
    java/com/vila/           # JUnit 5 tests
```

## Scene Mockups

### Login
Centered layout — VILA wordmark, username/password fields, Sign In and Create Account buttons. Minimal, no background clutter.

### Home / Storefront
Top navbar with brand name, search field, and bag button. Full-width warm-tone hero banner with headline and CTA. Product grid below.

### Product Detail
Split view — large image placeholder on the left (warm tone fill), product info panel on the right with name, price, size dropdown, and Add to Bag button.

### Cart / Bag
Split view — scrollable cart item list on the left, order summary sidebar on the right with total and checkout button.

## Database Schema (planned)

- `users` — id, username, password_hash, is_admin
- `products` — id, name, description, price, stock, category
- `orders` — id, user_id, created_at, total
- `order_items` — id, order_id, product_id, size, quantity, unit_price

## GitHub Repository

https://github.com/nazargeldy/project2cst338
