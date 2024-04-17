package Management;

import DatabaseConnection.databaseDataCon;
import Kitchen.*;


import java.lang.reflect.GenericDeclaration;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implemented class of the MNGInterface.
 * @see MNGInterface
 */
public class MNG implements MNGInterface {
    private PreparedStatement menuState, dishState, dishState2, recipeState, ingState, ingState2;
    private Connection con;
    private databaseDataCon database;

    /**
     * Method to close the connection to the Kitchen database.
     * @throws SQLException
     */
    private void closeConnection() throws SQLException {
        database.endConnection(con);
            }

    /**
     * Method to start the connection to the Kitchen database.
     * @throws SQLException
     */
    private void startConnection() throws SQLException {
        database = new databaseDataCon();
        con = new databaseDataCon().returnConnection();
            }

    /**
     * Method that allows the Management team to get a menu from the Kitchen database by a specified Menu ID.
     * Starts a connection to the database, selects the menu with the specific Menu ID.
     * The method then starts the process of creating a Menu object, iterating through the database to collect the Dishes related to the menu, the Recipes related to the Dishes, and then the Ingredients related to the Recipe.
     * This information is then compiled and created into a single Menu object.
     * @param menuID The ID of the menu.
     * @return A menu object.
     * @see Menu,Ingredient,Recipe,Dish
     * @throws SQLException
     */
    @Override
    public Menu getMenu(int menuID) throws SQLException {
        startConnection();
        int ID = 0;
        int creationDate = 0;
        List<Dish> dishes = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();
        menuState = con.prepareStatement("SELECT * FROM Menu WHERE ID=?");
        menuState.setInt(1, menuID);
        ResultSet menuResults = menuState.executeQuery();
        while (menuResults.next()) {
            ID = menuResults.getInt(1);
            creationDate = menuResults.getInt(2);


            //Second statement
            dishState = con.prepareStatement("SELECT DishID FROM MenuDish WHERE MenuID=?");
            dishState.setInt(1, menuID);
            ResultSet menuDishSet = dishState.executeQuery();
            while (menuDishSet.next()) {
                int dishID = menuDishSet.getInt(1);
                //FOR EVERY DISH INSIDE OF THE SET

                dishState2 = con.prepareStatement("SELECT * FROM Dish WHERE ID=?");
                dishState2.setInt(1, dishID);
                ResultSet dishSet = dishState2.executeQuery();

                while (dishSet.next()) {
                    String dishName = dishSet.getString(2);
                    int recipeID = dishSet.getInt(4);
                    recipeState = con.prepareStatement("SELECT * FROM Recipe WHERE ID=?");
                    recipeState.setInt(1, recipeID);
                    ResultSet recipeSet = recipeState.executeQuery();
                    recipeSet.next();
                    String recipeName = recipeSet.getString(2);

                    ingState = con.prepareStatement("SELECT * FROM RecipeIngredient WHERE RecipeID=?");
                    ingState.setInt(1, recipeID);
                    ResultSet ingredientSet = ingState.executeQuery();
                    while (ingredientSet.next()) {
                        int ingID = ingredientSet.getInt(2);

                        ingState2 = con.prepareStatement("SELECT * FROM Ingredient WHERE ID=?");
                        ingState2.setInt(1, ingID);
                        ResultSet ingResult = ingState2.executeQuery();
                        while(ingResult.next()){
                            String ingName = ingResult.getString(2);
                            double ingQuan = ingResult.getDouble(3);
                            Ingredient ingredient = new Ingredient(ingID, ingName, ingQuan);
                            ingredients.add(ingredient);
                        }

                    }
                    Recipe recipe = new Recipe(recipeID, recipeName, ingredients);
                    Dish dish = new Dish(dishID, dishName, recipe);
                    dishes.add(dish);
                }
            }
        }
        closeConnection();
        return new Menu(ID, creationDate, dishes);
    }
    /**
     * Method that allows the Management team to get a menu from the Kitchen database by a specified approval date.
     * Starts a connection to the database, selects the menu with the specified approval date.
     * The method then starts the process of creating a Menu object, iterating through the database to collect the Dishes related to the menu, the Recipes related to the Dishes, and then the Ingredients related to the Recipe.
     * This information is then compiled and created into a single Menu object.
     * @param approvalDate The approval date of the menu.
     * @return A menu object.
     * @see Menu,Ingredient,Recipe,Dish
     * @throws SQLException
     */
    @Override
    public Menu getMenuByDate(int approvalDate) throws SQLException {
        startConnection();
        int ID = 0;
        int creationDate = 0;
        List<Dish> dishes = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();
        menuState = con.prepareStatement("SELECT * FROM Menu WHERE ChefApprovalDate=?");
        menuState.setInt(1,approvalDate);


        ResultSet menuResults = menuState.executeQuery();
        while (menuResults.next()) {
            ID = menuResults.getInt(1);
            creationDate = menuResults.getInt(2);


            //Second statement
            dishState = con.prepareStatement("SELECT DishID FROM MenuDish WHERE MenuID=?");
            dishState.setInt(1, ID);
            ResultSet menuDishSet = dishState.executeQuery();
            while (menuDishSet.next()) {
                int dishID = menuDishSet.getInt(1);
                //FOR EVERY DISH INSIDE OF THE SET

                dishState2 = con.prepareStatement("SELECT * FROM Dish WHERE ID=?");
                dishState2.setInt(1, dishID);
                ResultSet dishSet = dishState2.executeQuery();

                while (dishSet.next()) {
                    String dishName = dishSet.getString(2);
                    int recipeID = dishSet.getInt(4);
                    recipeState = con.prepareStatement("SELECT * FROM Recipe WHERE ID=?");
                    recipeState.setInt(1, recipeID);
                    ResultSet recipeSet = recipeState.executeQuery();
                    recipeSet.next();
                    String recipeName = recipeSet.getString(2);

                    ingState = con.prepareStatement("SELECT * FROM RecipeIngredient WHERE RecipeID=?");
                    ingState.setInt(1, recipeID);
                    ResultSet ingredientSet = ingState.executeQuery();
                    while (ingredientSet.next()) {
                        int ingID = ingredientSet.getInt(2);

                        ingState2 = con.prepareStatement("SELECT * FROM Ingredient WHERE ID=?");
                        ingState2.setInt(1, ingID);
                        ResultSet ingResult = ingState2.executeQuery();
                        while(ingResult.next()){
                            String ingName = ingResult.getString(2);
                            double ingQuan = ingResult.getDouble(3);
                            Ingredient ingredient = new Ingredient(ingID, ingName, ingQuan);
                            ingredients.add(ingredient);
                        }

                    }
                    Recipe recipe = new Recipe(recipeID, recipeName, ingredients);
                    Dish dish = new Dish(dishID, dishName, recipe);
                    dishes.add(dish);
                }

            }
        }
        closeConnection();
        return new Menu(ID, creationDate, dishes);
    }
    /**
     * Method that allows the Management team to get a list of menus from the Kitchen database by a range of approval dates.
     * Starts a connection to the database, selects the menus inside the range of the initialDate and endDate.
     * The method then starts the process of creating a Menu object, iterating through the database to collect the Dishes related to the menu, the Recipes related to the Dishes, and then the Ingredients related to the Recipe.
     * For each Menu that is found, a new Menu object is created. These Menu objects are then compiled into a menuList that holds the various menu's that may have been found.
     * @param initialDate The initial date of the range.
     * @param endDate The end date of the range.
     * @return A List of Menu objects.
     * @see Menu,Ingredient,Recipe,Dish
     * @throws SQLException
     */

    @Override
    public List<Menu> getMenuRange(int initialDate, int endDate) throws SQLException {
        startConnection();
        List <Menu> menuList = new ArrayList<>();
        int ID = 0;
        int creationDate = 0;
        menuState = con.prepareStatement("SELECT * FROM Menu WHERE Creationdate BETWEEN ? AND ?");
        menuState.setInt(1,initialDate);
        menuState.setInt(2,endDate);
        ResultSet menuResults = menuState.executeQuery();
        while (menuResults.next()) {
            Menu menu;
            ID = menuResults.getInt(1);
            creationDate = menuResults.getInt(2);
            List<Dish> dishes = new ArrayList<>();
            List<Ingredient> ingredients = new ArrayList<>();


            //Second statement
            dishState = con.prepareStatement("SELECT DishID FROM MenuDish WHERE MenuID=?");
            dishState.setInt(1, ID);
            ResultSet menuDishSet = dishState.executeQuery();
            while (menuDishSet.next()) {
                int dishID = menuDishSet.getInt(1);
                //FOR EVERY DISH INSIDE OF THE SET

                dishState2 = con.prepareStatement("SELECT * FROM Dish WHERE ID=?");
                dishState2.setInt(1, dishID);
                ResultSet dishSet = dishState2.executeQuery();

                while (dishSet.next()) {
                    String dishName = dishSet.getString(2);
                    int recipeID = dishSet.getInt(4);
                    recipeState = con.prepareStatement("SELECT * FROM Recipe WHERE ID=?");
                    recipeState.setInt(1, recipeID);
                    ResultSet recipeSet = recipeState.executeQuery();
                    recipeSet.next();
                    String recipeName = recipeSet.getString(2);

                    ingState = con.prepareStatement("SELECT * FROM RecipeIngredient WHERE RecipeID=?");
                    ingState.setInt(1, recipeID);
                    ResultSet ingredientSet = ingState.executeQuery();
                    while (ingredientSet.next()) {
                        int ingID = ingredientSet.getInt(2);

                        ingState2 = con.prepareStatement("SELECT * FROM Ingredient WHERE ID=?");
                        ingState2.setInt(1, ingID);
                        ResultSet ingResult = ingState2.executeQuery();
                        while(ingResult.next()){
                            String ingName = ingResult.getString(2);
                            double ingQuan = ingResult.getDouble(3);
                            Ingredient ingredient = new Ingredient(ingID, ingName, ingQuan);
                            ingredients.add(ingredient);
                        }

                    }
                    Recipe recipe = new Recipe(recipeID, recipeName, ingredients);
                    Dish dish = new Dish(dishID, dishName, recipe);
                    dishes.add(dish);
                }

            }
            menu = new Menu(ID, creationDate, dishes);
            menuList.add(menu);
        }
        closeConnection();
        return menuList;
    }
    /**
     * Method that allows the Management team to get dish from the Kitchen database by a Dish ID.
     * Starts a connection to the database, selects the Dish with the specified Dish ID.
     * The method then starts the process of creating a Dish object, iterating through to obtain the data related to the dish. Starting with retrieving the Recipe, then the Ingredients related to the Recipe.
     * The specified Dish details (Recipe, Ingredients), are then compiled to create a Dish Object. 
     * @param dishID The unique identifier of a Dish.
     * @return A dish object.
     * @see Ingredient,Recipe,Dish
     * @throws SQLException
     */
    @Override
    public Dish getDish(int dishID) throws SQLException {
        startConnection();
        Recipe recipe = null;
        Dish dish;
        String dishName="No Dish";
        List<Ingredient> ingredients = null;
        dishState = con.prepareStatement("SELECT * FROM Dish WHERE ID=?");
        dishState.setInt(1,dishID);
        ResultSet dishResults = dishState.executeQuery();
        while(dishResults.next()){
            dishName = dishResults.getString(2);
            int recipeID = dishResults.getInt(4);
            recipeState = con.prepareStatement("SELECT * FROM Recipe WHERE ID=?");
            recipeState.setInt(1, recipeID);
            ResultSet recipeSet = recipeState.executeQuery();
            recipeSet.next();
            String recipeName = recipeSet.getString(2);


            ingState = con.prepareStatement("SELECT * FROM RecipeIngredient WHERE RecipeID=?");
            ingState.setInt(1, recipeID);
            ResultSet ingredientSet = ingState.executeQuery();
            while (ingredientSet.next()) {
                int ingID = ingredientSet.getInt(2);

                ingState2 = con.prepareStatement("SELECT * FROM Ingredient WHERE ID=?");
                ingState2.setInt(1, ingID);
                ResultSet ingResult = ingState2.executeQuery();
                while(ingResult.next()){
                    String ingName = ingResult.getString(2);
                    double ingQuan = ingResult.getDouble(3);
                    Ingredient ingredient = new Ingredient(ingID, ingName, ingQuan);
                    ingredients.add(ingredient);
                }

            }
            recipe = new Recipe(recipeID, recipeName, ingredients);

        }
        closeConnection();
        return new Dish(dishID, dishName,recipe);
    }
    /**
     * Method that allows the Management team to get an order from the Kitchen database by Order ID.
     * Starts a connection to the database, selects the Order with the specified Order ID.
     * The method then starts the process of creating a Order object, iterating through to obtain the data related to the Order. Starting with retrieving the OrdersIngredients to locate the Ingredient IDs related to the Order, then the Ingredients to retrieve the specific Ingredient data.
     * The data retrieved is then compiled into a single Order object.
     * @param orderID The unique identifier of a Dish.
     * @return An Order object.
     * @see Order,Ingredient
     * @throws SQLException
     */
    @Override
    public Order getOrder(int orderID) throws SQLException {
        startConnection();
        int creationDate=0;
        List<Ingredient> ingredients = new ArrayList<>();
        double intAmount=0;
        int ingID =0;
        String ingName = null;
        PreparedStatement orderState = con.prepareStatement("SELECT * FROM Orders WHERE ID=?");
        orderState.setInt(1,orderID);
        ResultSet orderResults = orderState.executeQuery();
        while(orderResults.next()){
            creationDate = orderResults.getInt(2);

            ingState = con.prepareStatement("SELECT * FROM OrdersIngredient WHERE OrderID=?");
            ingState.setInt(1,orderID);
            ResultSet ingSet = ingState.executeQuery();
            while(ingSet.next()){
                intAmount = ingSet.getInt(3);
                ingID = ingSet.getInt(2);
                ingState2 = con.prepareStatement("SELECT * FROM Ingredient WHERE ID=?");
                ingState2.setInt(1,ingID);
                ResultSet ingTableSet = ingState2.executeQuery();
                while(ingTableSet.next()){
                    ingName = ingTableSet.getString(2);
                    ingredients.add(new Ingredient(ingID,ingName,intAmount));
                    System.out.println("Adding ingredient: "+ingName);
                }


            }
        }
        closeConnection();
        return new Order(orderID,creationDate,ingredients);
    }

    /**
     * Method that allows the Management team to get an order from the Kitchen database by Date.
     * Starts a connection to the database, selects the Order with the specified Order Date.
     * The method then starts the process of creating a Order object, iterating through to obtain the data related to the Order. Starting with retrieving the OrdersIngredients to locate the Ingredient IDs related to the Order, then the Ingredients to retrieve the specific Ingredient data.
     * The data retrieved is then compiled into a single Order object.
     * @param orderDate The date when the order was created.
     * @return An Order object
     * @throws SQLException
     * @see Order,Ingredient
     */
    @Override
    public Order getOrderByDate(int orderDate) throws SQLException {
        startConnection();
        int orderID=0;
        List<Ingredient> ingredients = new ArrayList<>();
        double intAmount=0;
        int ingID =0;
        String ingName = null;
        PreparedStatement orderState = con.prepareStatement("SELECT * FROM Orders WHERE CreationDate=?");
        orderState.setInt(1,orderDate);
        ResultSet orderResults = orderState.executeQuery();
        while(orderResults.next()){
            orderID = orderResults.getInt(1);

            ingState = con.prepareStatement("SELECT * FROM OrdersIngredient WHERE OrderID=?");
            ingState.setInt(1,orderID);
            ResultSet ingSet = ingState.executeQuery();
            while(ingSet.next()){
                intAmount = ingSet.getInt(3);
                ingID = ingSet.getInt(2);
                ingState2 = con.prepareStatement("SELECT * FROM Ingredient WHERE ID=?");
                ingState2.setInt(1,ingID);
                ResultSet ingTableSet = ingState2.executeQuery();
                while(ingTableSet.next()){
                    ingName = ingTableSet.getString(2);
                    ingredients.add(new Ingredient(ingID,ingName,intAmount));
                    System.out.println("Adding ingredient: "+ingName);
                }
            }
        }
        closeConnection();
        return new Order(orderID,orderDate,ingredients);
    }

    /**
     * Method that allows the Management team to get a list of Orders from the Kitchen database by a range of approval dates.
     * Starts a connection to the database, selects the Orders inside the range of the initialDate and endDate.
     * The method then starts the process of creating a Order object, iterating through the database to collect the Ingredients related to the Orders.
     * For each Order that is found, a new Order object is created. These Order objects are then compiled into a list of Orders which contains Orders along with the respective Ingredients for the specific Order.
     * @param initialDate The initial date of the range.
     * @param endDate The end date of the range.
     * @return A List of Order objects
     * @see Order,Ingredient
     * @throws SQLException
     */
    @Override
    public List<Order> getOrderRange(int initialDate, int endDate) throws SQLException {
        startConnection();
        int orderID=0;
        List<Order> Orders = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();
        double intAmount=0;
        int ingID =0;
        int orderDate = 0;
        String ingName = null;
        PreparedStatement orderState = con.prepareStatement("SELECT * FROM Orders WHERE Creationdate BETWEEN ? AND ?");
        orderState.setInt(1,initialDate);
        orderState.setInt(2,endDate);
        ResultSet orderResults = orderState.executeQuery();
        while(orderResults.next()){
            orderID = orderResults.getInt(1);
            orderDate = orderResults.getInt(2);
            ingState = con.prepareStatement("SELECT * FROM OrdersIngredient WHERE OrderID=?");
            ingState.setInt(1,orderID);
            ResultSet ingSet = ingState.executeQuery();
            while(ingSet.next()){
                intAmount = ingSet.getInt(3);
                ingID = ingSet.getInt(2);
                ingState2 = con.prepareStatement("SELECT * FROM Ingredient WHERE ID=?");
                ingState2.setInt(1,ingID);
                ResultSet ingTableSet = ingState2.executeQuery();
                while(ingTableSet.next()){
                    ingName = ingTableSet.getString(2);
                    ingredients.add(new Ingredient(ingID,ingName,intAmount));
                    System.out.println("Adding ingredient: "+ingName);
                }
            }
            Orders.add(new Order(orderID,orderDate,ingredients));
        }
        closeConnection();
        return Orders;
    }
}
