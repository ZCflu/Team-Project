package Kitchen;

import DatabaseConnection.databaseAdmin;
import DatabaseConnection.databaseDataCon;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Depreciated Class. Not used/Functional.
 * @see KitchenGUI.Ticket
 * @deprecated
 * @see KitchenGUI.OrderMenu
 */
public class KitchenManagement {
        private Connection con;

    public KitchenManagement(){


    }

    /**
     * Method to start a connection to the dabaase.
     * @throws SQLException
     */
    private void startConnection() throws SQLException {
        con = new databaseDataCon().returnConnection();
    }

    /**
     * Method to end the connection to the database.
     * @throws SQLException
     */
    private void endConnection() throws SQLException {
        databaseAdmin database = new databaseAdmin();
    }

    /**
     * Depreciated method to create Ticket objects.
     */
    public void createTickets(){



    }
}
