package KitchenGUI;


import FOHtoKitchen.Order;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

public class Ticket extends JPanel {
    private JComboBox selectProgress;
    private Order order;
    private int gridYAmount;
    private String additionals;
    private int orderID;
    private List<FOHtoKitchen.Dish> dishList;
    private JLabel timeLabel;
    private JLabel dishesHeaderLabel;
    private OrderMenu orderMenu;
    LineBorder border = new LineBorder(Color.BLACK,2);
    public Ticket(int OrderID, List<FOHtoKitchen.Dish> dishes, String specialRequests, Order order,OrderMenu orderMenu){
        this.orderMenu = orderMenu;
        this.order=order;
        this.additionals=specialRequests;
        this.dishList = dishes;
        this.orderID=OrderID;
        addAttributes();
    }

    private void buttonsMake(){
        String[] options = { "Not Started", "Preparing", "Cooking", "Serving", "Done" };
        selectProgress = new JComboBox(options);
        JLabel select = new JLabel("Update Order: ");
        add(select,"wrap,newline 25");
        add(selectProgress,"wrap");
        JButton sendProgress = new JButton("Submit");
        ActionListener progressListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String option = selectProgress.getSelectedItem().toString();
                System.out.println(order.getOrderStatus());
                order.setOrderStatus(option);
            }
        };
        sendProgress.addActionListener(progressListener);

        add(sendProgress);


        JButton removeTicket = new JButton("Remove Ticket");
        ActionListener removeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                orderMenu.removeTicket(Ticket.this);
            }
        };

        removeTicket.addActionListener(removeListener);

        add(removeTicket,"wrap");
    }

    private void ticketBackground(){


    }

    private void addAttributes() {
        // Set background color before adding components
        //setBackground(Color.decode("#424242"));

        MigLayout mig = new MigLayout();
        setLayout(mig);
        setOpaque(true); // Ensure the panel is opaque
        setBorder(border);

        JLabel ticketNumber = new JLabel("Order ID: " + orderID);
        add(ticketNumber);
        JLabel tableNumber = new JLabel("Table Number: Not Available");
        add(tableNumber, "wrap");

        timeLabel = new JLabel("Time Alive: 00:00:00");
        add(timeLabel, "wrap");
        gridYAmount = 3;
        dishesHeaderLabel = new JLabel("Dishes:");

        add(dishesHeaderLabel, "wrap");
        addDishes();
        addAdditionalInformation();
        buttonsMake();
        timerSet();
    }

    private void addDishes(){
        for(FOHtoKitchen.Dish d : dishList){
            int quantity = d.getQuantity();
            JLabel dishQuantity = new JLabel(String.valueOf(quantity));
            add(dishQuantity);
            JLabel dishlabel = new JLabel(d.getName());
            add(dishlabel,"wrap");
        }
    }
    private void addAdditionalInformation(){
        if(!additionals.isEmpty()){
            JLabel important = new JLabel("Additional Information: ");
            add(important,"wrap,newline 25");
            JLabel additionalsLabel = new JLabel(additionals);
            add(additionalsLabel,"wrap");
        }
    }


    private void timerSet(){
        Timer timer = new Timer(1000, new ActionListener() {
            private long startTime = System.currentTimeMillis();

            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                updateElapsedTime(elapsedTime);
            }
        });
        timer.start();
    }
    private void updateElapsedTime(long elapsedTime) {
        long seconds = (elapsedTime / 1000) % 60;
        long minutes = (elapsedTime / (1000 * 60)) % 60;
        long hours = (elapsedTime / (1000 * 60 * 60)) % 24;

        DecimalFormat formatter = new DecimalFormat("00");
        String timeString = formatter.format(hours) + ":" + formatter.format(minutes) + ":" + formatter.format(seconds);
        timeLabel.setText("Time Alive: " + timeString);
    }
    public Order getOrder(){
        return order;
    }

}
