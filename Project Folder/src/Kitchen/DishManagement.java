package Kitchen;

import DatabaseConnection.databaseAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DishManagement {
    // Your database connection and other methods here

    private Connection con;
    private databaseAdmin database;
    private void closeConnection() throws SQLException {
        database.endConnection(con);
    }
    private void startConnection() throws SQLException {
        database = new databaseAdmin();
        con = new databaseAdmin().returnConnection();
    }


    public void AddDish(Dish dish) throws SQLException {
        startConnection();
        String insertQuery = "INSERT IGNORE INTO Dish (ID, Name) VALUES (?, ?)";

        try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
            insertStmt.setInt(1, dish.getDishID());
            insertStmt.setString(2, dish.getDishName());

            int rowsAffected = insertStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dish added successfully: " + dish.getDishName());
            } else {
                System.out.println("Failed to add dish: " + dish.getDishName() + ". It may already exist.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while adding dish: " + dish.getDishName());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void updateDishType(int dishID, String newType) throws SQLException {
        startConnection();
        String updateQuery = "UPDATE Dish SET Type = ? WHERE ID = ?";

        try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
            updateStmt.setString(1, newType);
            updateStmt.setInt(2, dishID);

            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dish type updated successfully for dish ID " + dishID);
            } else {
                System.out.println("No dish found with ID " + dishID);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while updating dish type for dish ID " + dishID);
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void updateDishRecipeID(int dishID, int newRecipeID) throws SQLException {
        startConnection();
        String updateQuery = "UPDATE Dish SET RecipeID = ? WHERE ID = ?";

        try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
            updateStmt.setInt(1, newRecipeID);
            updateStmt.setInt(2, dishID);

            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Recipe ID updated successfully for dish ID " + dishID);
            } else {
                System.out.println("No dish found with ID " + dishID);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while updating recipe ID for dish ID " + dishID);
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void updateDishAvailability(int dishID, int newAvailability) throws SQLException {
        startConnection();
        String updateQuery = "UPDATE Dish SET Availability = ? WHERE ID = ?";

        try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
            updateStmt.setInt(1, newAvailability);
            updateStmt.setInt(2, dishID);

            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Availability updated successfully for dish ID " + dishID);
            } else {
                System.out.println("No dish found with ID " + dishID);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while updating availability for dish ID " + dishID);
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void updateDishName(int dishID, String newName) throws SQLException {
        startConnection();
        String updateQuery = "UPDATE Dish SET Name = ? WHERE ID = ?";

        try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
            updateStmt.setString(1, newName);
            updateStmt.setInt(2, dishID);

            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Name updated successfully for dish ID " + dishID);
            } else {
                System.out.println("No dish found with ID " + dishID);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while updating name for dish ID " + dishID);
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void addDishID(int dishID) throws SQLException {
        startConnection();
        String insertQuery = "INSERT IGNORE INTO Dish (ID) VALUES (?)";

        try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
            insertStmt.setInt(1, dishID);

            int rowsAffected = insertStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dish ID added successfully: " + dishID);
            } else {
                System.out.println("Failed to add dish ID: " + dishID);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while adding dish ID: " + dishID);
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }


}

