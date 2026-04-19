package com.vila.controller;

import com.vila.SceneFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ProductDetailController {

    @FXML private Label productName;
    @FXML private Label productPrice;
    @FXML private ComboBox<String> sizeSelector;
    @FXML private Label sizeError;

    private SceneFactory sceneFactory;

    public void setSceneFactory(SceneFactory sceneFactory) {
        this.sceneFactory = sceneFactory;
    }

    @FXML
    private void initialize() {
        sizeSelector.getItems().addAll("XS", "S", "M", "L", "XL");
    }

    @FXML
    private void onAddToCart() {
        if (sizeSelector.getValue() == null) {
            sizeError.setText("Please select a size.");
            return;
        }
        sizeError.setText("");
        // TODO: add to cart session
    }

    @FXML
    private void onBack() throws Exception {
        if (sceneFactory != null) sceneFactory.showHome();
    }
}
