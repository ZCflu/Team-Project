package DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseDataCon extends DatabaseMNG {

    private String dataUser,dataPassword,url;
    public databaseDataCon() throws SQLException {
        url = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t17";
        dataUser = "in2033t17_d";
        dataPassword = "TkQX3QA1mag";
    }


    public Connection returnConnection() throws SQLException {
        con = DriverManager.getConnection(url,dataUser,dataPassword);
        return con;

    }
}
