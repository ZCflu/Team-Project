package KitchenGUI;
import DatabaseConnection.databaseConnect;
import Kitchen.*;
import Management.MNG;
import Management.MNGInterface;

import javax.sound.midi.Soundbank;
import java.sql.SQLException;
import java.util.List;

public class Main {


    public static void main(String[] args) throws SQLException {
        tests();
        MainMenu menu = new MainMenu();
    }


    private static void tests() throws SQLException {

        MNGInterface test = new MNG();
        Menu testmen = test.getMenu(1);
        Menu testmen2 = test.getMenuByDate(20240410);
        List<Menu> testmen3 = test.getMenuRange(20240409,20340409);
        Dish dishTest = test.getDish(3);
        Order orderTest = test.getOrder(2);
        Order orderTest2 = test.getOrderByDate(20240311);
        List<Order> orderTest3 = test.getOrderRange(20240311,21240350);

        System.out.println("Dish Test: "+dishTest.getDishName());
        List<Ingredient> ingList2 = orderTest2.getIngredients();
        List<Ingredient> ingList = orderTest.getIngredients();



        for (Order s : orderTest3){
            List<Ingredient> li = s.getIngredients();
            for(Ingredient i : li){
                System.out.println("Ingredient Complete Maybe?: "+ i.getIngredientName());
            }
        }
        for(Ingredient i : ingList){
            System.out.println("Ingredient: "+ i.getIngredientName());
        }
        for(Ingredient i : ingList2){
            System.out.println("Ingredient2: "+ i.getIngredientName());
        }
        for (Dish d : testmen.getDishes()){
            System.out.println("Dish1: "+d.getDishName());
        }
        for (Dish d : testmen2.getDishes()){
            System.out.println("Dish2: "+d.getDishName());
        }
        for (Menu m : testmen3){
            for (Dish d : testmen2.getDishes()){
                System.out.println("Dish3: "+d.getDishName());
            }
        }
    }

}