import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends Menu {
    private int currentMenuOpen=0;
    private JLabel kitchenLabel,welcomeLabel,selectOptionLabel;
    private JPanel buttonPanel;
    private ImageIcon bgImage,kitchenIcon;
    private JButton button1,button2,button3,button4;
    private ActionListener option1,option2,option3,option4;
    public MainMenu(){
        showMenu();
    }
    private void frameAttributes(){
        mouseListener();
        header();
        menuOptions();
    }
    private void showMenu(){
        frameAttributes();
        repaint();
        show();
    }


    private void header(){
        welcomeLabel = new JLabel("Welcome Chef");
        selectOptionLabel = new JLabel("Please select an option above");
        selectOptionLabel.setFont(new Font("Ariel",1,24));
        welcomeLabel.setFont(new Font("Ariel",1,24));
        kitchenIcon = new ImageIcon("data/Images/kitchenicon.png");
        kitchenLabel = new JLabel(addImages(kitchenIcon,1,1));
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        background.add(kitchenLabel,gbc);

        gbc.gridy = 1;
        background.add(welcomeLabel,gbc);

        gbc.gridy = 2;
        background.add(selectOptionLabel,gbc);
    }
    private void menuOptions() {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        button1 = new JButton("Kitchen Orders");
        button2 = new JButton("Menu Management");
        button3 = new JButton("Waste Management");
        button4 = new JButton("Option 4");


        // Set preferred size for buttons
        Dimension buttonSize = new Dimension(200, 50);
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);
        button4.setPreferredSize(buttonSize);

        // Set font for buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        button1.setFont(buttonFont);
        button2.setFont(buttonFont);
        button3.setFont(buttonFont);
        button4.setFont(buttonFont);

        button1.setBorder(null);
        button2.setBorder(null);
        button3.setBorder(null);
        button4.setBorder(null);

        button1.addActionListener(option1);
        button2.addActionListener(option2);
        button3.addActionListener(option3);
        button4.addActionListener(option4);


        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);

        add(buttonPanel, BorderLayout.NORTH);
    }

    private void mouseListener(){
        option1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMenuOpen=1;
                System.out.println("Current Orders");
                background.hide();
            }
        };
        option2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMenuOpen=2;

            }
        };
        option3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMenuOpen=3;

            }
        };
        option4 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMenuOpen=4;
                background.show();

            }
        };
    }
}
