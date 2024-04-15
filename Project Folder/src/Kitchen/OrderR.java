package Kitchen;

import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;

public class OrderR {
    private static final String JDBC_URL = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t17";;
    private static final String USERNAME = "in2033t17_d";
    private static final String PASSWORD = "TkQX3QA1mag";
    private static final long POLLING_INTERVAL = 60 * 1000; // 1 minute



    public void startPolling() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new PollingTask(), 0, POLLING_INTERVAL);
        System.out.println("Start Polling");
    }

    class PollingTask extends TimerTask {
        @Override
        public void run() {
            try {
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                statement.executeUpdate("DELETE FROM TableOrders WHERE ReadyToServe = 1");



                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}