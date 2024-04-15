package Kitchen;

import DatabaseConnection.databaseAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RecipeManagement {


    private Connection con;
    private databaseAdmin database;

    private void closeConnection() throws SQLException {
        database.endConnection(con);
    }

    private void startConnection() throws SQLException {
        database = new databaseAdmin();
        con = new databaseAdmin().returnConnection();
    }

    public void addRecipe(Recipe recipe) throws SQLException {
        startConnection();
        String insertQuery = "INSERT IGNORE INTO Recipe (ID, Name) VALUES (?, ?)";

        try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
            insertStmt.setInt(1, recipe.getRecipeID());
            insertStmt.setString(2, recipe.getRecipeName());

            int rowsAffected = insertStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Recipe added successfully: " + recipe.getRecipeName());
            } else {
                System.out.println("Failed to add dish: " + recipe.getRecipeName() + ". It may already exist.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while adding dish: " + recipe.getRecipeName());
            e.printStackTrace();
        } finally {
            closeConnection();
        }


    }

    public void addCreatedByToRecipe(int recipeID, String createdBy) throws SQLException {
        startConnection();
        String updateQuery = "UPDATE Recipe SET CreatedBy = ? WHERE ID = ?";

        try (PreparedStatement updateStmt = con.prepareStatement(updateQuery)) {
            updateStmt.setString(1, createdBy);
            updateStmt.setInt(2, recipeID);

            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Created by information added successfully to recipe with ID " + recipeID);
            } else {
                System.out.println("No recipe found with ID " + recipeID);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while adding created by information to recipe with ID " + recipeID);
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void addIngredientsToRecipe(int recipeID, List<Ingredient> ingredients) throws SQLException {
        startConnection();
        String insertQuery2 = "INSERT IGNORE INTO RecipeIngredient (RecipeID, IngredientID, Quantity) VALUES (?, ?, ?)";

        try (PreparedStatement insertStmt = con.prepareStatement(insertQuery2)) {
            for (Ingredient ingredient : ingredients) {
                insertStmt.setInt(1, recipeID);
                insertStmt.setInt(2, ingredient.getIngredientID());
                insertStmt.setDouble(3, ingredient.getQuantity());
                insertStmt.addBatch();
            }
            int[] rowsAffected = insertStmt.executeBatch();

            System.out.println("Ingredients added successfully to recipe with ID " + recipeID);
        } catch (SQLException e) {
            System.err.println("SQL Error while adding ingredients to recipe with ID " + recipeID);
            e.printStackTrace();
        }finally {


            String insertQuery = "UPDATE RecipeIngredient SET IngredientID = ?, Quantity = ? WHERE RecipeID = ?";

            try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
                for (Ingredient ingredient : ingredients) {
                    insertStmt.setInt(1, ingredient.getIngredientID());
                    insertStmt.setDouble(2, ingredient.getQuantity());
                    insertStmt.setInt(3, ingredient.getIngredientID());
                    insertStmt.addBatch();
                }
                int[] rowsAffected = insertStmt.executeBatch();

                System.out.println("Ingredients added successfully to recipe with ID " + recipeID);
            } catch (SQLException e) {
                System.err.println("SQL Error while adding ingredients to recipe with ID " + recipeID);
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
    }
}
