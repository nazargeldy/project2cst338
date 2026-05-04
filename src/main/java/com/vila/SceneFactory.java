package com.vila;

import com.vila.controller.CartController;
import com.vila.controller.HomeController;
import com.vila.controller.LoginController;
import com.vila.controller.ProductDetailController;
import com.vila.controller.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class SceneFactory {

    private final Stage stage;

    private static final double MIN_WIDTH = 1200;
    private static final double MIN_HEIGHT = 780;

    public SceneFactory(Stage stage) {
        this.stage = stage;
        stage.setTitle("Vila Activewear");
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
    }

    public void showLogin() throws Exception {
        switchTo("login.fxml");
    }

    public void showHome() throws Exception {
        switchTo("home.fxml");
    }

    public void showProductDetail() throws Exception {
        switchTo("product-detail.fxml");
    }

    public void showCart() throws Exception {
        switchTo("cart.fxml");
    }

    public void showRegister() throws Exception {
        switchTo("register.fxml");
    }

    private void switchTo(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/vila/" + fxml));
        Scene scene = new Scene(loader.load(), MIN_WIDTH, MIN_HEIGHT);
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/com/vila/styles.css")).toExternalForm()
        );

        // Inject SceneFactory into the controller so navigation works
        Object controller = loader.getController();
        if (controller instanceof LoginController c) {
            c.setSceneFactory(this);
        } else if (controller instanceof RegisterController c) {
            c.setSceneFactory(this);
        } else if (controller instanceof HomeController c) {
            c.setSceneFactory(this);
        } else if (controller instanceof ProductDetailController c) {
            c.setSceneFactory(this);
        } else if (controller instanceof CartController c) {
            c.setSceneFactory(this);
        }

        stage.setScene(scene);
        stage.show();
    }
}