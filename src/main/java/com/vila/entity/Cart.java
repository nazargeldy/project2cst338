/**
 * Represents the cart in the application
 * Three constructors!
 * Empty constructor is for frameworks (JavaFX)
 * I also added methods to increase and decrease the cart's product count
 *
 * @author Adrian Alonso
 * @version 1.0
 * @since 4/29/2025
 */

package com.vila.entity;

public class Cart {
    private int cartCount;

    private int productID;
    private int userID;
    private int cartID;

    /**
     * Constructor for frameworks
     */
    public Cart() {};

    /**
     * Constructor for new cart (local)
     * Used when a user adds an item/product to their cart
     *
     * @param cartCount The amount of the same product added to the cart
     * @param productID The product's id
     * @param userID The user's id
     */
    public Cart(int cartCount, int productID, int userID) {
        this.cartCount = cartCount;
        this.productID = productID;
        this.userID = userID;
    }

    /**
     * Constructor for already existing cart
     * The cart being updated (DAO)
     *
     * @param cartCount The amount of the same product added to the cart
     * @param productID The product's id
     * @param userID The user's id
     * @param cartID The cart's id
     */
    public Cart(int cartCount, int productID, int userID, int cartID) {
        this.cartCount = cartCount;
        this.productID = productID;
        this.userID = userID;

        this.cartID = cartID;
    }

    /**
     * Increases the cart's product count by 1
     */
    public void increaseCartCount() {
        this.cartCount++;
    }

    /**
     * Decreases the cart's product count by 1
     * Might not be needed (let me know if it needs to be removed)
     */
    public void decreaseCartCount() {
        this.cartCount--;
    }

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartCount=" + cartCount +
                ", productID=" + productID +
                ", userID=" + userID +
                ", cartID=" + cartID +
                '}';
    }
}
