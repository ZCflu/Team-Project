package DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseDataCon extends DatabaseMNG {

    private String dataUser,dataPassword,url;

    /**
     * Constructor that initialises password and username string to the data user database details.
     * @throws SQLException
     * @see databaseAdmin
     * @see DatabaseMNG
     */
    public databaseDataCon() throws SQLException {
        url = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t17";
        dataUser = "in2033t17_d";
        dataPassword = "TkQX3QA1mag";
    }

    /**
     * Creates a connection to the data user version of the database.
     * @return A Connection (con) used for preparing statements.
     * @throws SQLException
     */
    public Connection returnConnection() throws SQLException {
        con = DriverManager.getConnection(url,dataUser,dataPassword);
        return con;

    }

    public void endConnection(Connection con) {
    }
}
