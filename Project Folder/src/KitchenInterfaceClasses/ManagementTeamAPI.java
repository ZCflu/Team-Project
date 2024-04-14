package KitchenInterfaceClasses;

import MenuClasses.*;
import StaffClasses.StaffAviliability;
import StaffClasses.StaffMember;
import StaffClasses.loginCredentials;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class ManagementTeamAPI implements kitchenInterface {
    // Database credentials
    private static final String DATABASE_NAME = "in2033t18";
    private static final String DATABASE_USER = "in2033t18_d";
    private static final String DATABASE_PASSWORD = "XIr1e7E_35o";
    private static final String DATABASE_HOST = "smcse-stuproj00.city.ac.uk";
    private static final int DATABASE_PORT = 3306;

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://" + DATABASE_HOST + ":" + DATABASE_PORT + "/" + DATABASE_NAME;

    // Methods
    @Override
    public Vector<Ingredient> getFreshStock() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(DB_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Connected to database successfully.");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM ingredients";
            ResultSet rs = stmt.executeQuery(sql);

            Vector<Ingredient> ingredientList = new Vector<>();
            while (rs.next()) {
                Ingredient ingredient = new Ingredient(rs.getInt("ID"), rs.getString("Name"),
                        rs.getDouble("Price"));
                ingredientList.add(ingredient);
            }

            rs.close();
            stmt.close();
            conn.close();
            return ingredientList;
        } catch (SQLException se) {
            System.out.println("SQL Error: ");
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("Other Error: ");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Menu getLatestFoodMenu() {
        Connection conn = null;
        PreparedStatement stmt = null;
        Menu menu = new Menu(0, 0, 0);
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(DB_URL, DATABASE_USER, DATABASE_PASSWORD);

            System.out.println("Connected to database successfully.");

            // Get Menu Details, created by and chef approval date, as well final approval status
            String sql = "SELECT * FROM menus " +
                    "WHERE isApproved = 1 "+ // ensure that menu was approved
                    "ORDER BY creationDate DESC " + // Order by nearest date first
                    "LIMIT 1";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                menu.setID(rs.getInt("ID"));
                menu.setIsApproved(rs.getBoolean("IsApproved"));
                menu.setCreationDate(rs.getInt("creationDate"));
                menu.setChefApprovalDate(rs.getInt("chefApprovalDate"));
            }

            sql = "SELECT dishID FROM menu_dish WHERE menuID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, menu.getID());
            rs = stmt.executeQuery();
            while(rs.next()){
                menu.addDish(getDishDetailsByDishID(rs.getInt("dishID")));
            }
            // Clean up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public Menu getLatestDrinkMenu() { //UNDER CONSIDERATION
        return null;
    }

    @Override
    public Dish getDishDetailsByDishID(int dishID) {
        Dish dish = new Dish(dishID);
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(DB_URL, DATABASE_USER, DATABASE_PASSWORD);

            System.out.println("Connected to database successfully.");

            // Get Dish Details
            String sql = "SELECT * FROM `dishes` d LEFT JOIN `dishes_recipes` dr ON d.ID = dr.dishID " +
                    "LEFT JOIN `wine_dishes` wd ON wd.dishid = d.ID " +
                    "WHERE d.ID = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, dish.getID());
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                dish.setName(rs.getString("Name"));
                dish.setPrice(rs.getDouble("Price"));
                dish.setType(rs.getString("Type"));
                if(rs.getInt("recipeID") != 0){
                    dish.setRecipe(new Recipe(rs.getInt("recipeID"), "none"));
                    getRecipeDetails(dish.getRecipe());
                } else {
                    dish.setRecipe(new Recipe(0, "none"));
                }
                if(rs.getInt("wineID") != 0){
                    dish.setWineParing(new Wine(rs.getInt("wineID")));
                    getWine(dish.getWineParing());
                } else {
                    dish.setWineParing(new Wine(0));
                }
            }

            // Get Allergen Details
            sql = "SELECT allergen FROM dish_allergn WHERE dishID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, dish.getID());
            rs = stmt.executeQuery();
            HashSet<String> allergens = new HashSet<>();
            while(rs.next()){
                allergens.add(rs.getString("allergen"));
            }
            dish.setAllergenInfo(allergens);

            // Clean up environment
            rs.close();
            stmt.close();
            conn.close();
            return dish;
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
            return null;
        }
    }

    private void getRecipeDetails(Recipe recipe){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(DB_URL, DATABASE_USER, DATABASE_PASSWORD);

            System.out.println("Connected to database successfully.");

            //Update created by details
            String sql = "SELECT Createdby FROM recipes WHERE ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, recipe.getID());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                recipe.setCreatedBy(rs.getString("Createdby"));
            }
            // Update ingredients details
            sql = "SELECT i.Name, i.ID as InggredientID, i.Price, ri.quantity " +
                    "FROM ingredients i " +
                    "JOIN recipes_ingredients ri ON i.ID = ri.ingredientID " +
                    "JOIN recipes r ON ri.recipeID = r.ID " +
                    "WHERE r.ID = ?;";
            // Replace 'your_table' with your actual table name
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, recipe.getID());
            rs = stmt.executeQuery();
            HashMap<Ingredient, Double> ingredients = new HashMap<>();
            while(rs.next()){
                ingredients.put(new Ingredient(rs.getInt("InggredientID"), rs.getString("Name"),
                        rs.getDouble("Price")), rs.getDouble("quantity"));
            }
            recipe.setIngredients(ingredients);
            // Process the result set
            // Clean up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public static void getWine(Wine wine){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(DB_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Connected to database successfully.");
            String sql = "SELECT * FROM wine WHERE ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, wine.getID());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                wine.setName(rs.getString("Name"));
                wine.setType(rs.getString("Type"));
                wine.setProductionYear(rs.getInt("ProductionYear"));
                wine.setPrice(rs.getDouble("Price"));
            }
        } catch (SQLException se) {
            System.out.println("SQL Error: ");
            se.printStackTrace();
        } catch (Exception e){
            System.out.println("Other Error: ");
            e.printStackTrace();
        }

    }

    @Override
    public Dish getMostPopularDishWithingDateRange(int startDate, int endDate) {
        return null;
    }

    @Override
    public Vector<StaffMember> getStaffMembersByDate(int date) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Vector<StaffMember> staffMembers = new Vector<>();
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(DB_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Connected to database successfully.");

            String sql = "SELECT * FROM staffMember st " +
                    "JOIN staffAvailability sa ON sa.StaffMemberID = st.ID " +
                    "WHERE date = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, date);
            ResultSet rs = stmt.executeQuery();
            // Extract data from result set
            while(rs.next()) {
                StaffMember staffMember = new StaffMember(rs.getInt("ID"), rs.getString("name"),
                        rs.getString("surname"), rs.getString("position"),
                        rs.getString("email"));
                staffMember.addStaffAviliability(new StaffAviliability(staffMember, rs.getInt("date"),
                        rs.getInt("StartWorkingHours"), rs.getInt("EndWorkingHours")));
                staffMembers.add(staffMember);
            }
            // Clean up environment
            rs.close();
            stmt.close();
            conn.close();
            return staffMembers;
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
        catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Vector<StaffMember> getStaffMembersByPosition(String position) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Vector<StaffMember> staffMembers = new Vector<>();
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(DB_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Connected to database successfully.");
            String sql = "SELECT * FROM staffMember WHERE position = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, position);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                StaffMember staffMember = new StaffMember(rs.getInt("ID"), rs.getString("name"),
                        rs.getString("surname"), rs.getString("position"),
                        rs.getString("email"));
                getStaffAvaliabity(staffMember);
                staffMembers.add(staffMember);
            }
            // Clean up environment
            rs.close();
            stmt.close();
            conn.close();
            return staffMembers;
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
            return null;
        }
        catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public StaffMember getStaffMemberByCredentials(String login, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(DB_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Connected to database successfully.");
            String sql = "SELECT * FROM loginCredentials WHERE login = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){
                return null;
            }
            StaffMember staffMember = getStaffMemberByID(rs.getInt("StaffMemberID"));
            staffMember.setLoginCredentials(new loginCredentials(rs.getString("login"),
                    rs.getString("password")));
            // Clean up environment
            rs.close();
            stmt.close();
            conn.close();
            return staffMember;
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();}
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public StaffMember getStaffMemberByID(int ID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(DB_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Connected to database successfully.");
            String sql = "SELECT * FROM staffMember WHERE ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){
                return null;
            }
            StaffMember staffMember = new StaffMember(rs.getInt("ID"), rs.getString("name"),
                    rs.getString("surname"), rs.getString("position"),
                    rs.getString("email"));
            // Clean up environment
            rs.close();
            stmt.close();
            conn.close();
            getStaffAvaliabity(staffMember);
            return staffMember;

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
            return null;
        }
        catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
            return null;
        }
    }

    private void getStaffAvaliabity(StaffMember staffMember){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(DB_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Connected to database successfully.");
            String sql = "SELECT * FROM staffAvailability WHERE StaffMemberID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, staffMember.getID());
            ResultSet rs = stmt.executeQuery();
            Vector<StaffAviliability> staffAviliabilities = new Vector<>();
            while(rs.next()){
                staffAviliabilities.add(new StaffAviliability(staffMember, rs.getInt("date"),
                        rs.getInt("StartWorkingHours"), rs.getInt("EndWorkingHours")));
            }
            staffMember.setStaffAviliability(staffAviliabilities);
            // Clean up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
        catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }

    }
}
