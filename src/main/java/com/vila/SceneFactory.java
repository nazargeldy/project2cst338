package com.vila;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

    private void switchTo(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/vila/" + fxml));
        Scene scene = new Scene(loader.load(), MIN_WIDTH, MIN_HEIGHT);
        scene.getStylesheets().add(
            getClass().getResource("/com/vila/styles.css").toExternalForm()
        );
        stage.setScene(scene);
        stage.show();
    }
}
