package com.vila.controller;

import com.vila.SceneFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HomeController {

    @FXML private TextField searchField;

    private SceneFactory sceneFactory;

    public void setSceneFactory(SceneFactory sceneFactory) {
        this.sceneFactory = sceneFactory;
    }

    @FXML
    private void onSearch() {
        String query = searchField.getText().trim();
        // TODO: filter product grid by query
    }

    @FXML
    private void onCartClick() throws Exception {
        if (sceneFactory != null) sceneFactory.showCart();
    }

    @FXML
    private void onProductClick() throws Exception {
        if (sceneFactory != null) sceneFactory.showProductDetail();
    }
}
