package FrontOfHouse;

import DatabaseConnection.databaseDataCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FOH implements FOHInterface{
    private Connection con;
    public FOH() throws SQLException {
        databaseDataCon database = new databaseDataCon();
        con = database.returnConnection();
    }
    @Override
    public Map<String, Integer> getDishAvailabilityUpdates() throws SQLException {
        Map<String, Integer> map = new HashMap<>();
        PreparedStatement dishStatement = con.prepareStatement("SELECT * FROM Dish");
        ResultSet dishResults = dishStatement.executeQuery();
        while(dishResults.next()){
            String dishName = dishResults.getString(2);
            int dishAmount = dishResults.getInt(5);
            map.put(dishName,dishAmount);
        }
        return map;
    }

    @Override
    public Map<String, Boolean> confirmSpecialRequests(Map<String, String> specialRequests) {
        Map<String, Boolean> map = new HashMap<>();

        return map;
    }

    @Override
    public boolean notifyFOHOrderReady(String orderID, String tableID, Map<String, String> dishStatus) {
        return false;
    }

    @Override
    public void setFOHNotificationListener(FOHNotificationListener listener) {

    }
}
