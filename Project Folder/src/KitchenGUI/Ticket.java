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

/**
 * Class for generating a ticket object showing details such as the OrderID, TableID, Dishes, and any special requests. Also shows the time the ticket has been alive for.
 * @see OrderMenu
 */
public class Ticket extends JPanel {
    private JComboBox selectProgress;
    private Order order;
    private int gridYAmount;
    private String additionals;
    private int orderID,tableID;
    private List<FOHtoKitchen.Dish> dishList;
    private JLabel timeLabel;
    private JLabel dishesHeaderLabel;
    private OrderMenu orderMenu;
    LineBorder border = new LineBorder(Color.BLACK,2);

    /**
     * Constructor that initialises the orderMenu, order, specialRequests, list of dishes, and orderID to the object. Executed the addAttributes() method.
     * @param OrderID Unique identifier for an order.
     * @param dishes List of dishes an order contains.
     * @param specialRequests Request from the client to be catered for.
     * @param order Order object.
     * @param orderMenu OrderMenu object.
     */
    public Ticket(int OrderID, List<FOHtoKitchen.Dish> dishes, String specialRequests, Order order,OrderMenu orderMenu){
        this.orderMenu = orderMenu;
        this.order=order;
        this.additionals=specialRequests;
        this.dishList = dishes;
        this.orderID=OrderID;

        addAttributes();
    }

    /**
     * Method to create the buttons that can be selected on the Ticket object. Allowing the user to change the progress of a order, and remove the ticket from the panel it is in.
     * ActionListeners are added to the buttons, that execute the ability to change an order status, and remove the ticket.
     */
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

    /**
     * Method to select the attributes of the JPanel of the ticket. The layout is set to a new MigLayout. Details of the ticket are added here, along with methods addDishes(), addAdditionalInformation, buttonsMake(), and timerSet() to complete the layout of the Ticket.
     */

    private void addAttributes() {
        // Set background color before adding components
        //setBackground(Color.decode("#424242"));

        MigLayout mig = new MigLayout();
        setLayout(mig);
        setOpaque(true); // Ensure the panel is opaque
        setBorder(border);

        JLabel ticketNumber = new JLabel("Order ID: " + orderID);
        add(ticketNumber);
        JLabel tableNumber = new JLabel("Table Number: Not Available" + tableID);
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

    /**
     * Method to add dishes to the JPanel. For each dish, the quantity is also added.
     */
    private void addDishes(){
        for(FOHtoKitchen.Dish d : dishList){
            int quantity = d.getQuantity();
            JLabel dishQuantity = new JLabel(String.valueOf(quantity));
            add(dishQuantity);

            JLabel dishlabel = new JLabel(d.getName());
            add(dishlabel,"wrap");
        }
    }

    /**
     * Method to add the additional information (special requests) to the JPanel.
     */
    private void addAdditionalInformation(){
        if(!additionals.isEmpty()){
            JLabel important = new JLabel("Additional Information: ");
            add(important,"wrap,newline 25");
            JLabel additionalsLabel = new JLabel(additionals);
            add(additionalsLabel,"wrap");
        }
    }

    /**
     * Method to create and start the timer on how long the ticket has been alive on the screen for. Executes the method updateElapsedTime().
     */
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

    /**
     * Method to divide the elapsed time into the layout: 00:00:00.
     * @param elapsedTime
     */
    private void updateElapsedTime(long elapsedTime) {
        long seconds = (elapsedTime / 1000) % 60;
        long minutes = (elapsedTime / (1000 * 60)) % 60;
        long hours = (elapsedTime / (1000 * 60 * 60)) % 24;

        DecimalFormat formatter = new DecimalFormat("00");
        String timeString = formatter.format(hours) + ":" + formatter.format(minutes) + ":" + formatter.format(seconds);
        timeLabel.setText("Time Alive: " + timeString);
    }

    /**
     * Getter that allows the retrieval of the Order this ticket is for.
     * @return An order object.
     */
    public Order getOrder(){
        return order;
    }

}
