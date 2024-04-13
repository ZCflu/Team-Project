package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMNG {
    protected Connection con;

    public void endConnection(Connection con) throws SQLException {
        con.close();
        System.out.println("Database Connection Closed");
    }
}
