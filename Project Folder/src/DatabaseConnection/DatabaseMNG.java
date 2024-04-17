package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMNG {
    protected Connection con;

    /**
     * Closes the connection to the database.
     * @param con Takes a connection that is initialised in databaseAdmin.java or databaseDataCon.java
     * @throws SQLException
     * @see databaseAdmin,databaseDataCon
     */
    public void endConnection(Connection con) throws SQLException {
        con.close();
        System.out.println("Database Connection Closed");
    }
}
