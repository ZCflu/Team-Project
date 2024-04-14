package KitchenGUI;

import Kitchen.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class OrderTicket extends JPanel {
    private JLabel ticketNumber;
    private JLabel dishLabels;
    private List<Dish> dishes;
    private int tableNumber;
    private tableOrder order;
    public OrderTicket(tableOrder order){
        this.order=order;
        this.tableNumber = order.getTableID();
        this.dishes = order.getOrders();
        setAttributes();
        ticketAttributes();
    }
    private void setAttributes(){
        setLayout(new GridLayout(dishes.size()+1,1));
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
    private void ticketAttributes(){
        ticketNumber = new JLabel(String.valueOf(tableNumber));
        add(ticketNumber);
        repaint();
    }

}
