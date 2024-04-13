package FOHtoKitchen;

import java.util.List;

public interface kitchenInterface {

    // method to send order to the kitchen
    List<Order> getOrders();

    // method to update status of the order
    Order updateBookingStatus();
}
