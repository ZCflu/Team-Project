package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnect {

    private String adminUser,adminPass,dataUser,dataPassword,url;
    private Connection con;
    public databaseConnect() throws SQLException {
        url = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t17";
        adminUser = "in2033t17_a";
        adminPass = "C48WNAKOzaU";
        dataUser = "in2033t17_d";
        dataPassword = "TkQX3QA1mag";



    }


    public Connection returnConnection() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t17","in2033t17_a","C48WNAKOzaU");
        return con;

    }
}
