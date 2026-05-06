package com.vila.controller;

import com.vila.AppState;
import com.vila.ProductImages;
import com.vila.SceneFactory;
import com.vila.entity.Product;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.InputStream;

public class ProductDetailController implements Navigable {

    @FXML private Label productName;
    @FXML private Label productPrice;
    @FXML private Label productDescription;
    @FXML private ComboBox<String> sizeSelector;
    @FXML private Label sizeError;
    @FXML private StackPane imageContainer;

    private SceneFactory sceneFactory;

    @Override
    public void setSceneFactory(SceneFactory factory) {
        this.sceneFactory = factory;
    }

    @FXML
    private void initialize() {
        sizeSelector.getItems().addAll("XS", "S", "M", "L", "XL");

        Product p = AppState.selectedProduct;
        if (p != null) {
            productName.setText(p.getName());
            productPrice.setText(String.format("$%d", (int) p.getPrice()));
            productDescription.setText(p.getDescription());
            loadImage(p);
        }
    }

    private void loadImage(Product p) {
        if (imageContainer == null) return;
        String path = ProductImages.get(p.getName());
        if (path == null) return;
        InputStream stream = getClass().getResourceAsStream(path);
        if (stream == null) return;
        try {
            Image img = new Image(stream);
            ImageView iv = new ImageView(img);
            iv.fitWidthProperty().bind(imageContainer.widthProperty());
            iv.fitHeightProperty().bind(imageContainer.heightProperty());
            iv.setPreserveRatio(true);
            imageContainer.getChildren().add(0, iv);
        } catch (Exception ignored) {}
    }

    @FXML
    private void onAddToCart() {
        if (sizeSelector.getValue() == null) {
            sizeError.setText("Please select a size.");
            return;
        }
        sizeError.setText("Added to bag.");
    }

    @FXML
    private void onBack() throws Exception {
        if (sceneFactory != null) sceneFactory.showHome();
    }
}
