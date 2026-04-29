package com.vila.entity;

import javafx.beans.property.*;

public class Product {

    private final IntegerProperty productId;
    private final StringProperty name;
    private final StringProperty description;
    private final DoubleProperty price;
    private final IntegerProperty quantity;
    private final StringProperty category;

    public Product() {
        this.productId   = new SimpleIntegerProperty();
        this.name        = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.price       = new SimpleDoubleProperty();
        this.quantity    = new SimpleIntegerProperty();
        this.category    = new SimpleStringProperty();
    }

    public Product(int productId, String name, String description,
                   double price, int quantity, String category) {
        this.productId   = new SimpleIntegerProperty(productId);
        this.name        = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.price       = new SimpleDoubleProperty(price);
        this.quantity    = new SimpleIntegerProperty(quantity);
        this.category    = new SimpleStringProperty(category);
    }

    public int getProductId() { return productId.get(); }
    public void setProductId(int v) { productId.set(v); }
    public IntegerProperty productIdProperty() { return productId; }

    public String getName() { return name.get(); }
    public void setName(String v) { name.set(v); }
    public StringProperty nameProperty() { return name; }

    public String getDescription() { return description.get(); }
    public void setDescription(String v) { description.set(v); }
    public StringProperty descriptionProperty() { return description; }

    public double getPrice() { return price.get(); }
    public void setPrice(double v) { price.set(v); }
    public DoubleProperty priceProperty() { return price; }

    public int getQuantity() { return quantity.get(); }
    public void setQuantity(int v) { quantity.set(v); }
    public IntegerProperty quantityProperty() { return quantity; }

    public String getCategory() { return category.get(); }
    public void setCategory(String v) { category.set(v); }
    public StringProperty categoryProperty() { return category; }

    @Override
    public String toString() {
        return "Product{id=" + getProductId() + ", name='" + getName() + "', price=" + getPrice() + "}";
    }
}
