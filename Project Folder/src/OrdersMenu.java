import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class OrdersMenu extends Menu{
    private JLabel ordersBackground;
    public OrdersMenu(){
        tableOrder orderTest = new tableOrder(1,1,true,new Dish[]{new Dish(1,"DishTest",new Recipe(1,"RecipeTest",new Ingredient[]{new Ingredient(1,"IngredientTest",1)}))});
        OrderTicket ticketTest = new OrderTicket(orderTest);

    }
    public void showOrders(){

    }
    public void hideOrders(){

    }



}
