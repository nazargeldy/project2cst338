package com.vila.controller;

import com.vila.AppState;
import com.vila.Database;
import com.vila.ProductImages;
import com.vila.SceneFactory;
import com.vila.dao.ProductDAO;
import com.vila.entity.Product;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeController implements Navigable {

    @FXML private TextField searchField;
    @FXML private FlowPane productGrid;
    @FXML private Button tabAll;
    @FXML private Button tabBottoms;
    @FXML private Button tabTops;
    @FXML private Button tabHoodies;
    @FXML private Button tabJackets;

    private SceneFactory sceneFactory;
    private List<Product> allProducts = new ArrayList<>();
    private Button[] filterTabs;

    @Override
    public void setSceneFactory(SceneFactory factory) {
        this.sceneFactory = factory;
    }

    @FXML
    public void initialize() {
        filterTabs = new Button[]{tabAll, tabBottoms, tabTops, tabHoodies, tabJackets};
        loadProducts();
        renderProducts("All");
    }

    private void loadProducts() {
        ProductDAO dao = new ProductDAO(Database.getConnection());
        allProducts = dao.getAllProducts();
        if (allProducts.isEmpty()) {
            seedProducts(dao);
            allProducts = dao.getAllProducts();
        }
    }

    private void seedProducts(ProductDAO dao) {
        dao.addProduct(new Product(0, "Drift Pant",          "Wide leg, elastic waist. Side pockets. Wear them out.",            98.0,  60, "Bottoms"));
        dao.addProduct(new Product(0, "Transit Pant",        "Slim tapered, 5-pocket stretch. From gym to street.",             112.0,  45, "Bottoms"));
        dao.addProduct(new Product(0, "Rally Jogger",        "French terry, tapered leg, logo detail. In black.",               88.0,  55, "Bottoms"));
        dao.addProduct(new Product(0, "Easy Jogger",         "Same cut as Rally, in chalk. Oversized, relaxed feel.",           88.0,  40, "Bottoms"));
        dao.addProduct(new Product(0, "Studio Wide-Leg",     "High-rise waistband, fluid tech fabric. Yoga and beyond.",       108.0,  35, "Bottoms"));
        dao.addProduct(new Product(0, "Court Tee",           "Heavyweight pima blend. Relaxed cut, runs slightly large.",       48.0,  80, "Tops"));
        dao.addProduct(new Product(0, "Halftrack Zip",       "Modal-blend half-zip, grey heather. Runs a little oversized.",    88.0,  50, "Tops"));
        dao.addProduct(new Product(0, "Terrain Quarter-Zip", "Ultrasoft fleece back, bone colorway. Layering essential.",       84.0,  45, "Tops"));
        dao.addProduct(new Product(0, "Current Hoodie",      "Heavyweight French terry. Kangaroo pocket, oversized hood.",     128.0,  30, "Hoodies"));
        dao.addProduct(new Product(0, "Ground Crew",         "350gsm fleece crewneck. Rich espresso. Minor logo.",              96.0,  40, "Hoodies"));
        dao.addProduct(new Product(0, "Rest Day Crew",       "Grey heather crewneck. The one you'll reach for every weekend.", 104.0,  38, "Hoodies"));
        dao.addProduct(new Product(0, "Ridge Puffer",        "Quilted shell, matte finish. Wind and light rain.",              178.0,  20, "Jackets"));
    }

    private void renderProducts(String category) {
        productGrid.getChildren().clear();
        allProducts.stream()
                .filter(p -> category.equals("All") || p.getCategory().equalsIgnoreCase(category))
                .forEach(p -> productGrid.getChildren().add(createProductCard(p)));
    }

    private VBox createProductCard(Product p) {
        StackPane imageArea = new StackPane();
        imageArea.setPrefSize(252, 316);
        imageArea.setStyle("-fx-background-color: #E8E4DE;");

        String imgPath = ProductImages.get(p.getName());
        if (imgPath != null) {
            InputStream stream = getClass().getResourceAsStream(imgPath);
            if (stream != null) {
                try {
                    Image img = new Image(stream);
                    ImageView iv = new ImageView(img);
                    iv.setFitWidth(252);
                    iv.setFitHeight(316);
                    iv.setPreserveRatio(false);
                    imageArea.getChildren().add(iv);
                } catch (Exception ignored) {}
            }
        }

        Label tag = new Label(p.getCategory().toUpperCase());
        tag.getStyleClass().add("card-category-tag");
        StackPane.setAlignment(tag, Pos.BOTTOM_LEFT);
        StackPane.setMargin(tag, new Insets(0, 0, 10, 10));
        imageArea.getChildren().add(tag);

        Label name = new Label(p.getName());
        name.getStyleClass().add("card-name");

        Label price = new Label(String.format("$%d", (int) p.getPrice()));
        price.getStyleClass().add("card-price");

        VBox info = new VBox(5, name, price);
        info.getStyleClass().add("card-info");

        VBox card = new VBox(0, imageArea, info);
        card.getStyleClass().add("product-card");
        card.setPrefWidth(252);
        card.setOnMouseClicked(e -> {
            AppState.selectedProduct = p;
            navigateToDetail();
        });

        return card;
    }

    private void navigateToDetail() {
        try {
            if (sceneFactory != null) sceneFactory.showProductDetail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setActiveTab(Button clicked) {
        for (Button tab : filterTabs) {
            tab.getStyleClass().remove("filter-tab-active");
            if (!tab.getStyleClass().contains("filter-tab")) tab.getStyleClass().add("filter-tab");
        }
        clicked.getStyleClass().remove("filter-tab");
        if (!clicked.getStyleClass().contains("filter-tab-active")) clicked.getStyleClass().add("filter-tab-active");
    }

    @FXML private void onFilterAll()      { setActiveTab(tabAll);      renderProducts("All"); }
    @FXML private void onFilterBottoms()  { setActiveTab(tabBottoms);  renderProducts("Bottoms"); }
    @FXML private void onFilterTops()     { setActiveTab(tabTops);     renderProducts("Tops"); }
    @FXML private void onFilterHoodies()  { setActiveTab(tabHoodies);  renderProducts("Hoodies"); }
    @FXML private void onFilterJackets()  { setActiveTab(tabJackets);  renderProducts("Jackets"); }
    @FXML private void onFilterWomen()    { setActiveTab(tabAll);      renderProducts("All"); }
    @FXML private void onFilterMen()      { setActiveTab(tabAll);      renderProducts("All"); }

    @FXML
    private void onSearch() {
        String q = searchField.getText().trim().toLowerCase();
        if (q.isEmpty()) { renderProducts("All"); return; }
        productGrid.getChildren().clear();
        allProducts.stream()
                .filter(p -> p.getName().toLowerCase().contains(q)
                          || p.getCategory().toLowerCase().contains(q)
                          || p.getDescription().toLowerCase().contains(q))
                .forEach(p -> productGrid.getChildren().add(createProductCard(p)));
    }

    @FXML
    private void onCartClick() throws Exception {
        if (sceneFactory != null) sceneFactory.showCart();
    }
}
