package KitchenGUI;
import DatabaseConnection.databaseAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import KitchenInterfaceClasses.ManagementTeamAPI;
import MenuClasses.Ingredient;

import javax.swing.*;

/**
 * Class that updates Kitchen database with stock from the management team.
 */
public class InventoryManagement{

    ManagementTeamAPI mng = new ManagementTeamAPI();

    private PreparedStatement stock;

    private Connection con;
    private databaseAdmin database;

    /**
     * Method to close the connection to the database.
     * @see databaseAdmin
     * @throws SQLException
     */
    private void closeConnection() throws SQLException {
        database.endConnection(con);
    }

    /**
     * Method that starts the connection to the database.
     * @see databaseAdmin
     * @throws SQLException
     */
    private void startConnection() throws SQLException {
        database = new databaseAdmin();
        con = new databaseAdmin().returnConnection();
    }

    /**
     * Method to retrieve fresh stock from the Management team's database. A connection is established with the Kitchen database, and attempts to inset it into the Ingredient table.
     * @throws SQLException
     */
    public void getStock() throws SQLException{
        startConnection(); // Open database connection

        String insertQuery = "INSERT INTO Ingredient (Name, ID) VALUES (?, ?) ON DUPLICATE KEY UPDATE Name = VALUES(Name), ID = VALUES(ID)";
        try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
            for (Ingredient ingredient : mng.getFreshStock()) {
                // Set the parameters for the insert query
                insertStmt.setString(1, ingredient.getName());
                insertStmt.setInt(2, ingredient.getID()); // Assuming there's a method to get ID in Ingredient class

                // Execute the insert statement
                int rowsAffected = insertStmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Ingredient added to table: " + ingredient.getName());
                } else {
                    System.out.println("No rows affected for: " + ingredient.getName());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding ingredient to table");
            e.printStackTrace();
        }

        closeConnection(); // Close database connection
    }

    /**
     * Method to update stock in the Kitchen Database. Starts a database connection and uses the provided parameters to select a specific ingredient by name and update the available quantity of the said ingredient.
     * @param name Name of the ingredient.
     * @param available Available number of the ingredient.
     * @throws SQLException
     */
    public void updateStock(String name, int available) throws SQLException {
        startConnection(); // Open database connection

        String updateQuery = "UPDATE Ingredient SET AvailableQuantity = ? WHERE Name = ?";

        try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
            // Set the parameters for the update query
            updateStmt.setInt(1, available);
            updateStmt.setString(2, name);

            // Execute the update statement
            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Stock updated for: " + name);
            } else {
                System.out.println("No rows affected for: " + name);
            }
        } catch (SQLException e) {
            System.err.println("Error updating stock for: " + name);
            e.printStackTrace();
        }

        closeConnection(); // Close database connection
    }


}
