package FrontOfHouse;

import DatabaseConnection.databaseDataCon;
import Kitchen.DatabasePolling;
import Kitchen.OrderUpdateListener;
import Kitchen.OrderUpdater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Interface the kitchen provided to the Front of House.
 * @see FOHInterface
 */

public class FOH implements FOHInterface{

    private PreparedStatement menuState, dishStatement, orderStatement;
    private Connection con;
    private databaseDataCon database;

    private DatabasePolling databasePolling;

    /**
     * Method to close the connection to the database.
     * @see databaseDataCon
     * @throws SQLException
     */
    private void closeConnection() throws SQLException{

        database.endConnection(con);
    }

    /**
     * Method to start open a connection to the database.
     * @see databaseDataCon
     * @throws SQLException
     */

    private void startConnection() throws SQLException {
        database = new databaseDataCon();
        con = new databaseDataCon().returnConnection();
    }
    /**
     * Constructor that creates a new database connection, and initialises a Connection object.
     * @throws SQLException
     */
    public FOH() throws SQLException {
        databaseDataCon database = new databaseDataCon();
        con = database.returnConnection();
    }

    /**
     * Getter for Dish Availability.
     * @return A Map that contains a key (Dish name) and value (Dish Amount).
     * @throws SQLException
     */
    @Override
    public Map<String, Integer> getDishAvailabilityUpdates() throws SQLException {
        Map<String, Integer> map = new HashMap<>();
        dishStatement = con.prepareStatement("SELECT * FROM Dish");
        ResultSet dishResults = dishStatement.executeQuery();
        while(dishResults.next()){
            String dishName = dishResults.getString(2);
            int dishAmount = dishResults.getInt(5);
            map.put(dishName,dishAmount);
        }
        return map;
    }

    /**
     * Method that creates a map with special requests and whether they can be fulfilled or not.
     * @param specialRequests A map containing special request details (e.g., request ID, request description).
     * @return A map (String, Boolean). Key being the special request ID. Value is true or false fulfillment.
     */
    @Override
    public Map<String, Boolean> confirmSpecialRequests(Map<String, String> specialRequests) throws SQLException {
        Map<String, Boolean> map = new HashMap<>();
        String SpecialRequest = "a";
        String SpecialRequestID = "a";
        specialRequests = new HashMap<>();

        Boolean specialRequestFullfillment;
        orderStatement = con.prepareStatement("SELECT * FROM `Orders` ");
        ResultSet orderResults = orderStatement.executeQuery();
        while(orderResults.next())
        {
            SpecialRequest = orderResults.getString(4);
            specialRequestFullfillment = orderResults.getBoolean(5);
            SpecialRequestID = orderResults.getString(7);
            specialRequests.put(SpecialRequestID, SpecialRequest);

            map.put(SpecialRequestID, specialRequestFullfillment);

        }

        return map;
    }

    /**
     * Method that returns the status of an order in the kitchen.
     * @param OrderID ID of the order.
     * @param TableID ID of the table.
     * @return A boolean, which represents whether a Order is ready or not. True being ready, false being not ready.
     * @throws SQLException
     */
    @Override
    public boolean checkFOhOrderStatus(int OrderID, int TableID) throws SQLException {

        boolean orderStatus = false;
        orderStatement = con.prepareStatement("SELECT ReadyToServe FROM TableOrders WHERE OrderID = ? AND TableID = ?");

        orderStatement.setInt(1,OrderID);
        orderStatement.setInt(2, TableID);
        try (ResultSet orderResults = orderStatement.executeQuery()) {
            if (orderResults.next()) {
                orderStatus = orderResults.getBoolean("ReadyToServe");
            } else {
                // Handle case where no matching order is found
                System.out.println("No order found for OrderID " + OrderID + " and TableID " + TableID);
            }
        }

        System.out.println("Status of order no " + OrderID  + " for table no " + TableID + " is " + orderStatus);
        return orderStatus;
    }

    /**
     * Method to notify the front of house on the status of the orders in the kitchen.
     * @see OrderUpdateListener,OrderUpdater
     * @param listener
     */
    @Override
    public void notifyFohAboutOrders(OrderUpdateListener listener)
    {
        databasePolling = new DatabasePolling(listener);
        databasePolling.startPolling();
    }

}