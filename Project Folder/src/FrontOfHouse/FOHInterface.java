package FrontOfHouse;

import Kitchen.OrderUpdateListener;
import java.sql.SQLException;
import java.util.Map;

/**
 * FrontOfHouse.FOHInterface is an interface that defines methods for communication between the Kitchen and Front of House (FOH) systems.
 */
public interface FOHInterface {

    /**
     * Method to retrieve up-to-the-minute updates on dish availability.
     *
     * @return A map containing Item IDs as keys and availability status IDs as values.
     */
    Map<String, Integer> getDishAvailabilityUpdates() throws SQLException;

    /**
     * Method to send and confirm special requests.
     *
     * @param specialRequests A map containing special request details (e.g., request ID, request description).
     * @return A map containing special request IDs as keys and confirmation status (true if kitchen can fulfill, false otherwise) as values.
     */
    Map<String, Boolean> confirmSpecialRequests(Map<String, String> specialRequests) throws SQLException;

    /**
     * Method to check status of an order.
     *
     * @param orderID ID of the order.
     * @param tableID ID of the table.
     * @return A boolean indicating the outcome.
     */
    boolean checkFOhOrderStatus(int orderID, int tableID) throws SQLException;


    /**
     * Method to get notification if an order is ready.
     *      * I have provided listeners class as well
     * @param listener An OrderUpdateListener
     * @see Kitchen.OrderUpdateListener,Kitchen.OrderUpdater
     */
    void notifyFohAboutOrders(OrderUpdateListener listener);

}