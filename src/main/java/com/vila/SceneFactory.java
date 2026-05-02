package com.vila;

import com.vila.controller.Navigable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
        Parent root = loader.load();
        Object ctrl = loader.getController();
        if (ctrl instanceof Navigable nav) {
            nav.setSceneFactory(this);
        }
        Scene scene = new Scene(root, MIN_WIDTH, MIN_HEIGHT);
        scene.getStylesheets().add(
            getClass().getResource("/com/vila/styles.css").toExternalForm()
        );
        stage.setScene(scene);
        stage.show();
    }
}
