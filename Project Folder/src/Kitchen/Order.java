package Kitchen;

import DatabaseConnection.databaseConnect;

import java.util.List;

/**
 * The Kitchen.Order class represents an order placed for ingredients from the kitchen.
 */
public class Order {
    private databaseConnect database;
    private char IDType; // Type identifier ('O' for order)
    private int orderID; // Unique identifier for the order
    private int orderDate; // Date when the order was placed
    private String orderStatus; // Status of the order
    private List<Ingredient> items; // Array of Kitchen.Ingredient objects representing the items ordered

    /**
     * Constructor to initialize an Kitchen.Order object with provided details.
     * 
     * @param orderID Unique identifier for the order.
     * @param orderDate Date when the order was placed.
     * @param ingredients Array of Kitchen.Ingredient objects representing the items ordered.
     */
    public Order(int orderID, int orderDate, List<Ingredient> ingredients) {
        this.IDType = 'O'; // Setting IDType to 'O' for order
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.items = ingredients;
        this.orderStatus = "Ordered"; // Setting initial order status
    }

    /**
     * Getter method to retrieve the status of the order.
     * 
     * @return Status of the order.
     */
    public String getOrderStatus() {
        return this.orderStatus;
    }

    /**
     * Getter method to retrieve the date when the order was placed.
     * 
     * @return Date when the order was placed.
     */
    public int getOrderDate() {
        return this.orderDate;
    }

    /**
     * Getter method to retrieve the array of ingredients ordered.
     * 
     * @return Array of Kitchen.Ingredient objects representing the items ordered.
     */
    public List<Ingredient> getIngredients() {
        return items;
    }

    /**
     * Getter method to retrieve the type identifier of the order.
     * 
     * @return Type identifier ('O' for order).
     */
    public char getIDType() {
        return this.IDType;
    }

    /**
     * Getter method to retrieve the ID of the order.
     * 
     * @return Unique identifier for the order.
     */
    public int getOrderID() {
        return this.orderID;
    }
}
