package KitchenGUI;

import FrontOfHouse.FOH;
import Kitchen.*;
import Management.MNG;
import Management.MNGInterface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws SQLException {
        //tests1();
        //tests2();
        Authentication auth = new Authentication();
        //MNG mng = new MNG();
        //Menu m = mng.getMenu(1);
        //System.out.println(m.getCreationDate());
        //OrderUpdateListener listener = new OrderUpdater();
      /*  DishManagement add = new DishManagement();
        Dish d1 = new Dish(2, "Chicken Korma", "Main Dish", 1020);
        System.out.println(d1.getDishAvailability());
        add.AddDish(d1);
        add.updateDishType(d1.getDishID(), d1.getDishType());
        add.updateDishRecipeID(d1.getDishID(), d1.getRecipeID());
        add.updateDishAvailability(d1.getDishID(), 12);
        d1.setDishAvailability(12);
        d1.deductAvailability(5);
        add.updateDishAvailability(d1.getDishID(), d1.getDishAvailability());
        add.addDishID(1);*/

        /*RecipeManagement rp = new RecipeManagement();
        List<Ingredient> l = new ArrayList<>();
        l.add(new Ingredient(1, "carrot", 10));
        Recipe r = new Recipe(2, "Fries", l);

        rp.addRecipe(r);
        rp.addIngredientsToRecipe(r.getRecipeID(), l);*/
        OrderR r = new OrderR();
        r.startPolling();
        OrderManagement om = new OrderManagement();
        om.addOrder();
        om.updateOrder(39);




        //FOH foh = new FOH();
        //foh.notifyFohAboutOrders(listener);
        //foh.checkFOhOrderStatus(6, 11);


        //MainMenu menu = new MainMenu("HELLO");
    }


    private static void tests1() throws SQLException {
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
    private static void tests2() throws SQLException {
        FOH foh = new FOH();
        Map<String,Integer> map = foh.getDishAvailabilityUpdates();
        for (String s : map.keySet()){
            System.out.println("Dish: "+s+" "+"Availability: "+map.get(s));
        }
    }

}