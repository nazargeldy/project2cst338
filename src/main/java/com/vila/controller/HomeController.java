package com.vila.controller;

import com.vila.Database;
import com.vila.SceneFactory;
import com.vila.dao.ProductDAO;
import com.vila.entity.Product;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeController implements Navigable {

    @FXML private TextField searchField;
    @FXML private FlowPane productGrid;
    @FXML private Button tabAll;
    @FXML private Button tabLeggings;
    @FXML private Button tabSportsBras;
    @FXML private Button tabTops;
    @FXML private Button tabOuterwear;
    @FXML private Button tabAccessories;

    private SceneFactory sceneFactory;
    private List<Product> allProducts = new ArrayList<>();
    private Button[] filterTabs;

    private static final Map<String, String> CARD_COLORS = Map.of(
        "Leggings",   "#D4C8BE",
        "Shorts",     "#C8CEC8",
        "Sports Bra", "#C8D4CE",
        "Hoodies",    "#C8C4D0",
        "Tops",       "#D8D4C8",
        "Jackets",    "#C4C8D4",
        "Accessories","#D0CCCA"
    );

    @Override
    public void setSceneFactory(SceneFactory factory) {
        this.sceneFactory = factory;
    }

    @FXML
    public void initialize() {
        filterTabs = new Button[]{tabAll, tabLeggings, tabSportsBras, tabTops, tabOuterwear, tabAccessories};
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
        dao.addProduct(new Product(0, "Vista Tights",    "High-waist, 4-way stretch",         88.0,  50, "Leggings"));
        dao.addProduct(new Product(0, "Move Shorts",     "5-inch inseam, liner included",      58.0,  40, "Shorts"));
        dao.addProduct(new Product(0, "Studio Bra",      "Medium support, removable cups",     62.0,  35, "Sports Bra"));
        dao.addProduct(new Product(0, "Form Hoodie",     "Brushed fleece, relaxed fit",       118.0,  25, "Hoodies"));
        dao.addProduct(new Product(0, "Ridge Tank",      "Lightweight, sweat-wicking",         42.0,  60, "Tops"));
        dao.addProduct(new Product(0, "Terra Zip",       "Wind-resistant, two-way zip",       148.0,  20, "Jackets"));
        dao.addProduct(new Product(0, "Pace Tights",     "Reflective details, mesh panels",    94.0,  45, "Leggings"));
        dao.addProduct(new Product(0, "Core Belt Bag",   "1L capacity, adjustable strap",      36.0,  80, "Accessories"));
        dao.addProduct(new Product(0, "Align Long Sleeve","Buttery-soft, four-way stretch",    78.0,  55, "Tops"));
        dao.addProduct(new Product(0, "Summit Jacket",   "Lightweight, packable, DWR finish", 164.0,  15, "Jackets"));
        dao.addProduct(new Product(0, "Flow Bra",        "Light support, racerback",           52.0,  45, "Sports Bra"));
        dao.addProduct(new Product(0, "Stride Shorts",   "7-inch, side pockets",               64.0,  38, "Shorts"));
    }

    private void renderProducts(String category) {
        productGrid.getChildren().clear();
        allProducts.stream()
                .filter(p -> category.equals("All") || p.getCategory().equalsIgnoreCase(category))
                .forEach(p -> productGrid.getChildren().add(createProductCard(p)));
    }

    private VBox createProductCard(Product p) {
        String bg = CARD_COLORS.getOrDefault(p.getCategory(), "#EDE9E3");

        StackPane imageArea = new StackPane();
        imageArea.setPrefSize(252, 308);
        imageArea.setStyle("-fx-background-color: " + bg + ";");

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
        card.setOnMouseClicked(e -> navigateToDetail());

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
            if (!tab.getStyleClass().contains("filter-tab")) {
                tab.getStyleClass().add("filter-tab");
            }
        }
        clicked.getStyleClass().remove("filter-tab");
        if (!clicked.getStyleClass().contains("filter-tab-active")) {
            clicked.getStyleClass().add("filter-tab-active");
        }
    }

    @FXML private void onFilterAll()         { setActiveTab(tabAll);          renderProducts("All"); }
    @FXML private void onFilterLeggings()    { setActiveTab(tabLeggings);     renderProducts("Leggings"); }
    @FXML private void onFilterSportsBras()  { setActiveTab(tabSportsBras);   renderProducts("Sports Bra"); }
    @FXML private void onFilterTops()        { setActiveTab(tabTops);         renderProducts("Tops"); }
    @FXML private void onFilterOuterwear()   { setActiveTab(tabOuterwear);    renderProducts("Jackets"); }
    @FXML private void onFilterAccessories() { setActiveTab(tabAccessories);  renderProducts("Accessories"); }

    @FXML private void onFilterWomen() { setActiveTab(tabAll); renderProducts("All"); }
    @FXML private void onFilterMen()   { setActiveTab(tabAll); renderProducts("All"); }

    @FXML
    private void onSearch() {
        String q = searchField.getText().trim().toLowerCase();
        if (q.isEmpty()) {
            renderProducts("All");
            return;
        }
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
