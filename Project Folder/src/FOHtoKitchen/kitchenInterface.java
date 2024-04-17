package FOHtoKitchen;

import java.util.List;

/**
 * Interface from the Front of House. Contains methods for getting orders and updating booking status.
 */
public interface kitchenInterface {
    /**
     * Getter for a list of Order objects.
     * @return A list of Order objects.
     */

    // method to send order to the kitchen
    List<Order> getOrders();

    /**
     * Updates the status of an order. (Not implemented).
     * @return When implemented returns an order object. Currently returns null.
     */
    // method to update status of the order
    Order updateBookingStatus();
}
