package com.vila;

import javafx.application.Application;
import javafx.stage.Stage;

public class VilaApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SceneFactory factory = new SceneFactory(stage);
        factory.showLogin();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
