package com.vila.controller;

import com.vila.SceneFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CartController {

    @FXML private ListView<String> cartList;
    @FXML private Label totalLabel;

    private SceneFactory sceneFactory;

    public void setSceneFactory(SceneFactory sceneFactory) {
        this.sceneFactory = sceneFactory;
    }

    @FXML
    private void onCheckout() {
        // TODO: initiate checkout flow
    }

    @FXML
    private void onContinueShopping() throws Exception {
        if (sceneFactory != null) sceneFactory.showHome();
    }
}
