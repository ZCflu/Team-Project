package KitchenGUI;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import Kitchen.*;

public class Ticket extends JPanel {
    private int gridYAmount;
    private List<Dish> dishList;
    private JLabel timeLabel;
    private JLabel dishesHeaderLabel;
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    LineBorder border = new LineBorder(Color.BLACK,2);
    LayoutManager layoutManager;
    public Ticket(List<Dish> dishes){
        this.dishList = dishes;
        addAttributes();
    }
    private void addAttributes(){
        setOpaque(false);
        setBorder(border);
        setLayout(gbl);
        gbc.insets = new Insets(10, 0, 10, 10);
        gbc.gridy = 1;
        JLabel ticketNumber = new JLabel("KitchenGUI.Ticket Number:  1");
        add(ticketNumber,gbc);

        gbc.gridy=2;
        JLabel tableNumber = new JLabel("Table Number:  1");
        add(tableNumber,gbc);

        gbc.gridx = 3;
        gbc.gridy=1;
        timeLabel = new JLabel("Time Alive: 00:00:00");
        add(timeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy=3;
        gridYAmount =3;
        dishesHeaderLabel = new JLabel("Dishes:");

        add(dishesHeaderLabel, gbc);
        addDishes();
        timerSet();
    }
    private void addDishes(){

        for(Dish d : dishList){
            JLabel dishlabel = new JLabel(d.getDishName());
            gbc.gridy = gridYAmount+1;
            add(dishlabel,gbc);

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

}
