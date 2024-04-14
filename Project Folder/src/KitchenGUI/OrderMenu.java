package KitchenGUI;
import FOHtoKitchen.Dish;
import FOHtoKitchen.KitchenAPI;
import FOHtoKitchen.Order;
import Kitchen.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderMenu extends JPanel {
    private int ticketAmount;
    private int ticketTest;
    private JPanel ticketsPanel;
    private int ticketsPerRow = 6; // Adjust as needed
    private int refreshIntervalInSeconds = 30; // Adjust as needed
    private Timer refreshTimer;
    private List<Integer> currentOrders;

    public OrderMenu() {
        currentOrders = new ArrayList<>();
        setBackground(Color.decode("#3d4547"));
        setLayout(new BorderLayout());
        ticketsPanel = new JPanel();
        ticketsPanel.setLayout(new GridLayout(0, ticketsPerRow)); // 0 rows, as many columns as needed
        add(new JScrollPane(ticketsPanel), BorderLayout.NORTH); // Add a scroll pane to handle overflow
        ticketAmount = 0;
        ticketTest = 5;
        showOrders();
        startRefreshTimer();
    }

    private void startRefreshTimer() {
        refreshTimer = new Timer(refreshIntervalInSeconds * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshOrders(); // Refresh orders periodically
            }
        });
        refreshTimer.setInitialDelay(30); // Start refreshing immediately
        refreshTimer.start();
    }
    private void refreshOrders() {
        // Clear existing tickets
        //ticketsPanel.removeAll();
        //ticketAmount = 0;
        // Update tickets
        showOrders();

        // Repaint the panel
        revalidate();
        repaint();
    }
    public void showOrders(){
        KitchenAPI kitchApi = new KitchenAPI();
        List<FOHtoKitchen.Order> Orders = kitchApi.getOrders();
        for(FOHtoKitchen.Order a:Orders){
                int OrderID = a.getOrderID();
                if (!currentOrders.contains(OrderID)){
                    currentOrders.add(OrderID);
                    Map<Dish,Integer> dishes = a.getItems();
                    List<Dish> Dishes = new ArrayList<>();
                    for(Dish d : dishes.keySet()){
                        Dishes.add(d);
                    }
                    String additionals = a.getAdditionalInformation();
                    Ticket ticket = new Ticket(OrderID,Dishes,additionals,a,this);
                    addTicket(ticket);
                }
        }

    }

    private void addTicket(Ticket ticket) {
        ticketsPanel.add(ticket);
        ticketAmount++;
    }
    public void removeTicket(Ticket ticket){
        ticketsPanel.remove(ticket);
        int ID = ticket.getOrder().getOrderID();
        currentOrders.remove(Integer.valueOf(ID));
        //currentOrders.remove(ticket.getOrder().getOrderID());
        ticketAmount--;
        revalidate();
        repaint();
    }
}
