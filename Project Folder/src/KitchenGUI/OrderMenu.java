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

/**
 * Class that displays the current tickets that are in the Front of House management database. Allowing the Kitchen to change the status of the order, and remove the ticket.
 */
public class OrderMenu extends JPanel {
    private int ticketAmount;
    private int ticketTest;
    private JPanel ticketsPanel;
    private int ticketsPerRow = 6; // Adjust as needed
    private int refreshIntervalInSeconds = 30; // Adjust as needed
    private Timer refreshTimer;
    private List<Integer> currentOrders;

    /**
     * Constructor to initialise a new array list of current orders. The background colour of the frame is set to match the palette of Lancaster's logo. The layout is set to a BorderLayout. A new panel is created that will display each ticket with the limit of 6 tickets per row.
     * A JScrollPane is added to the ticketsPanel to ensure you can scroll down if there is an overflow off screen.
     * The showOrders() and startRefreshTimer() is executed.
     */
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

    /**
     * Method that creates and starts the timer for refreshing the tickets shown on the JPanel. The default delay is set to 10 seconds. Timer executes the refreshOrders() method.
     */
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

    /**
     * Method that executes the showOrders() method, then revalidates and paints the panel to show new tickets.
     */
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

    /**
     * Method that initialises the API recieved from the Front of House. Created a new List of orders. For each order the dishes are added to an array list. Once all dishes are added to the list, a new ticket is created and the addTicket() method is called.
     * If an Order is already present on screen, it will not be refreshed, allowing the timer to be consistent.
     * @see KitchenAPI
     * @see Order
     */
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

    /**
     * Method that adds a ticket to the ticket panel, then increments the number of tickets that are currently shown.
     * @param ticket Ticket object containing parameters of the OrderID, Dishes linked to the order, additional information, and the orderMenu object.
     * @see Ticket
     */
    private void addTicket(Ticket ticket) {
        ticketsPanel.add(ticket);
        ticketAmount++;
    }

    /**
     * Method to remove a ticket from the ticket panel. First removed the individual ticket, then removed the ticketID from the currentOrders list. Decrements the ticketAmount. Revalidates and repaints the JPanel to refresh the page.
     * @param ticket Ticket object reference to be removed from the JPanel.
     * @see Ticket
     */
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
