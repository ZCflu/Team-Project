package Kitchen;

import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Polls database every minute. Updating Orders in the database automatically with whether an Order is ready from the Kitchen.
 */
public class DatabasePolling {
    private static final String JDBC_URL = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t17";;
    private static final String USERNAME = "in2033t17_d";
    private static final String PASSWORD = "TkQX3QA1mag";
    private static final long POLLING_INTERVAL = 20 * 1000; // 1 minute

    private OrderUpdateListener listener;
    private int lastProcessedOrderID;

    /**
     * Constructor that takes initialises the OrderUpdateListener.
     * @param listener OrderUpdateListener Object.
     * @see OrderUpdater
     * @see OrderUpdateListener
     */
    public DatabasePolling(OrderUpdateListener listener) {
        this.listener = listener;
    }

    /**
     * Method to start a timer that updates the database automatically. Interval is set to 1 Minute in the POLLING_INTERVAL variable.
     */
    public void startPolling() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new PollingTask(), 0, POLLING_INTERVAL);
        System.out.println("Start Polling");
    }

    /**
     * PollingTask class that runs the statements to update the database automatically.
     */
    class PollingTask extends TimerTask {
        @Override
        public void run() {
            try {
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet resultSet = statement.executeQuery("SELECT * FROM TableOrders WHERE ReadyToServe = 1 AND orderID > '" + lastProcessedOrderID + "'");

                while(resultSet.next()) {
                    // Notify the listener about the updated rows
                    int orderID = resultSet.getInt(2);
                    int  TableID = resultSet.getInt(1);
                    // Add more fields as needed
                    listener.onOrderUpdate(orderID, TableID);


                    lastProcessedOrderID = resultSet.getInt(2);
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}