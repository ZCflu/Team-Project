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

public class FOH implements FOHInterface{

    private PreparedStatement menuState, dishStatement, orderStatement;
    private Connection con;
    private databaseDataCon database;

    private DatabasePolling databasePolling;


    private void closeConnection() throws SQLException{

        database.endConnection(con);
    }

    private void startConnection() throws SQLException {
        database = new databaseDataCon();
        con = new databaseDataCon().returnConnection();
    }

    public FOH() throws SQLException {
        databaseDataCon database = new databaseDataCon();
        con = database.returnConnection();
    }
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

    @Override
    public void notifyFohAboutOrders(OrderUpdateListener listener)
    {
        databasePolling = new DatabasePolling(listener);
        databasePolling.startPolling();
    }

}
