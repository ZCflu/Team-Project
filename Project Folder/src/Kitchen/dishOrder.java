package Kitchen;

/**
 * The Kitchen.dishOrder class represents an order for a specific dish in the restaurant.
 * It contains information about the order ID, dish ID, any special requests associated with the order,
 * fulfillment status of special requests, and whether the dish is ready to be served.
 */
public class dishOrder {
    private int orderID; // Unique identifier for the order
    private int dishID; // Unique identifier for the dish in the order
    private String specialRequest; // Any special request associated with the order
    private boolean specialRequestFulfillment; // Indicates whether the special request has been fulfilled
    private boolean readyToServe; // Indicates whether the dish is ready to be served

    /**
     * Constructor to initialize a Kitchen.dishOrder object with provided special request, order ID, and dish ID.
     * 
     * @param specialRequest Any special request associated with the order.
     * @param orderID Unique identifier for the order.
     * @param dishID Unique identifier for the dish in the order.
     */
    public dishOrder(String specialRequest, int orderID, int dishID) {
        this.specialRequest = specialRequest;
        this.orderID = orderID;
        this.dishID = dishID;
    }

    /**
     * Getter method to retrieve the fulfillment status of special requests for the dish order.
     * 
     * @return true if the special request has been fulfilled, false otherwise.
     */
    public boolean getSpecialRequestFulfillment() {
        return this.specialRequestFulfillment;
    }

    /**
     * Getter method to retrieve the readiness status of the dish for serving.
     * 
     * @return true if the dish is ready to be served, false otherwise.
     */
    public boolean readyToServe() {
        return this.readyToServe;
    }
}

