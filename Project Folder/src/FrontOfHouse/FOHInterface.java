package FrontOfHouse;

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
    public Map<String, Integer> getDishAvailabilityUpdates() throws SQLException;

    /**
     * Method to send and confirm special requests.
     * 
     * @param specialRequests A map containing special request details (e.g., request ID, request description).
     * @return A map containing special request IDs as keys and confirmation status (true if kitchen can fulfill, false otherwise) as values.
     */
    public Map<String, Boolean> confirmSpecialRequests(Map<String, String> specialRequests);

    /**
     * Method to notify FOH when a dish or an entire order is ready to be served.
     * 
     * @param orderID ID of the order.
     * @param tableID ID of the table.
     * @param dishStatus A map containing dish IDs as keys and their preparation status as values.
     * @return A boolean indicating successful notification.
     */
    public boolean notifyFOHOrderReady(String orderID, String tableID, Map<String, String> dishStatus);

    /**
     * Method to set up communication mechanism for FOH to be notified when a course is ready for serving.
     * 
     * @param listener A listener interface to receive notifications.
     */
    public void setFOHNotificationListener(FOHNotificationListener listener);

    /**
     * Listener interface for FOH notifications. Nested interface.
     */
    public interface FOHNotificationListener {
        /**
         * Method to receive notification when a course is ready for serving.
         * 
         * @param orderID ID of the order.
         * @param tableID ID of the table.
         * @param courseName Name of the course ready for serving.
         */
        public void onCourseReady(String orderID, String tableID, String courseName);
    }
}

