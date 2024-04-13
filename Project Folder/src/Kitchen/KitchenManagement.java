package Kitchen;

import DatabaseConnection.databaseAdmin;
import DatabaseConnection.databaseDataCon;

import java.sql.Connection;
import java.sql.SQLException;

public class KitchenManagement {
        private Connection con;

    public KitchenManagement(){


    }
    private void startConnection() throws SQLException {
        con = new databaseDataCon().returnConnection();
    }

    private void endConnection() throws SQLException {
        databaseAdmin database = new databaseAdmin();
    }

    public void createTickets(){



    }
}
