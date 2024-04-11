package KitchenGUI;
import Kitchen.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMenu extends JPanel {
    private int ticketAmount;
    private int ticketTest;
    private JPanel ticketsPanel;
    private int ticketsPerRow = 7; // Adjust as needed

    public OrderMenu() {
        setLayout(new BorderLayout());
        ticketsPanel = new JPanel();
        ticketsPanel.setLayout(new GridLayout(0, ticketsPerRow)); // 0 rows, as many columns as needed
        add(new JScrollPane(ticketsPanel), BorderLayout.NORTH); // Add a scroll pane to handle overflow

        ticketAmount = 0;
        ticketTest = 5;
        testTickets();
    }

    private void testTickets() {
        for (int i = 0; i < ticketTest; i++) {
            addTicket();
        }

    }

    private void addTicket() {
        //ticketsPanel.add(test);
        ticketAmount++;
    }
}
