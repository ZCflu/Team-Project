package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseAdmin extends DatabaseMNG{

    private String adminUser,adminPass,dataUser,dataPassword,url;
    public databaseAdmin() throws SQLException {
        url = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t17";
        adminUser = "in2033t17_a";
        adminPass = "C48WNAKOzaU";
    }


    public Connection returnConnection() throws SQLException {
        con = DriverManager.getConnection(url,adminUser,adminPass);
        return con;

    }
}
