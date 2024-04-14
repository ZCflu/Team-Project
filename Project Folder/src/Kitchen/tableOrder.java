package Kitchen;

import java.util.List;

/**
 * The Kitchen.tableOrder class represents an order associated with a specific table in the restaurant.
 * It contains information about the table ID, order ID, whether the dishes should be sent together,
 * and an array of Kitchen.Dish objects representing the dishes ordered at the table.
 */
public class tableOrder {
    private int tableID; // Identifier for the table associated with the order
    private int orderID; // Unique identifier for the order
    private boolean sendTogether; // Indicates whether the dishes should be sent together
    private List<dish> orders; // Array of Kitchen.Dish objects representing the dishes ordered at the table

    /**
     * Constructor to initialize a Kitchen.tableOrder object with provided table ID, order ID, sendTogether flag, and array of dishes.
     * 
     * @param tableID Identifier for the table associated with the order.
     * @param orderID Unique identifier for the order.
     * @param sendTogether Indicates whether the dishes should be sent together.
     * @param dishes Array of Kitchen.Dish objects representing the dishes ordered at the table.
     */
    public tableOrder(int tableID, int orderID, boolean sendTogether, List<dish> dishes) {
        this.tableID = tableID;
        this.orderID = orderID;
        this.sendTogether = sendTogether;
        this.orders = dishes;
    }

    /**
     * Getter method to retrieve the table ID associated with the order.
     * 
     * @return Identifier for the table associated with the order.
     */
    public int getTableID() {
        return this.tableID;
    }

    /**
     * Getter method to retrieve the order ID.
     * 
     * @return Unique identifier for the order.
     */
    public int getOrderID() {
        return this.orderID;
    }

    /**
     * Getter method to retrieve whether the dishes should be sent together.
     * 
     * @return true if the dishes should be sent together, false otherwise.
     */
    public boolean getSendTogether() {
        return this.sendTogether;
    }

    /**
     * Getter method to retrieve the array of Kitchen.Dish objects representing the dishes ordered at the table.
     *
     * @return Array of Kitchen.Dish objects representing the dishes ordered at the table.
     */
    public List<dish> getOrders() {
        return this.orders;
    }
}

