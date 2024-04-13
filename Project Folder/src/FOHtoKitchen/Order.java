package FOHtoKitchen;

import java.util.HashMap;
import java.util.Map;

// Abstract class representing an order
public class Order {

    // Unique identifier for the order
    private int orderID;
    // Type identifier for the order ('O' for Order)
    private char IDType = 'O';
    // Attributes related to the order details
    private String orderStatus;
    private String additionalInformation;
    // Map to store items and their quantities in the order
    private Map<Dish, Integer> items;
    // Table number associated with the order
    private int tableNo;

    public Order(int orderID, String orderStatus, int tableNo){
        this.orderID = orderID;
        this.orderStatus = orderStatus;
        this.items = new HashMap<>();
        this.tableNo = tableNo;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }
    // Getter method for additionalRequest
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    // Setter method for additionalRequest
    public void setAdditionalInformation(String additionalRequest) {
        this.additionalInformation = additionalRequest;
    }

    // Getter method for orderID
    public int getOrderID() {
        return orderID;
    }

    // Getter method for IDType
    public char getIDType() {
        return IDType;
    }

    // Getter method for orderStatus
    public String getOrderStatus() {
        return orderStatus;
    }

    public Map<Dish, Integer> getItems(){
        return items;
    }

    public void setItems(Map<Dish, Integer> items) {
        this.items = items;
    }
    // Setter method for orderStatus
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    // Method to update order items after the order has been placed

}