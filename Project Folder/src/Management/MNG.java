package Management;

import DatabaseConnection.databaseConnect;
import Kitchen.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MNG implements MNGInterface {
    private PreparedStatement menuState, dishState, dishState2, recipeState, ingState, ingState2;
    private Connection con;
    private databaseConnect database;

    public MNG() {
        databaseConnect database;
        {
            try {
                database = new databaseConnect();
                con = database.returnConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public Menu getMenu(int menuID) throws SQLException {
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
        return new Menu(ID, creationDate, dishes);
    }

    @Override
    public Menu getMenuByDate(int approvalDate) throws SQLException {
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
        return new Menu(ID, creationDate, dishes);
    }


    @Override
    public List<Menu> getMenuRange(int initialDate, int endDate) throws SQLException {
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
        return menuList;
    }

    @Override
    public Dish getDish(int dishID) throws SQLException {
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
        return new Dish(dishID, dishName,recipe);
    }

    @Override
    public Order getOrder(int orderID) throws SQLException {
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
        return new Order(orderID,creationDate,ingredients);
    }

    @Override
    public Order getOrderByDate(int orderDate) throws SQLException {
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
        return new Order(orderID,orderDate,ingredients);
    }

    @Override
    public List<Order> getOrderRange(int initialDate, int endDate) throws SQLException {
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
        return Orders;
    }
}
