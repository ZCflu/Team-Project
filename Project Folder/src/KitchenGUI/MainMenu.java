package KitchenGUI;

import KitchenGUI.GUIMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends GUIMenu {
    private String username;
    private int currentMenuOpen;
    private OrderMenu orderMenu;
    private JLabel kitchenLabel,welcomeLabel,selectOptionLabel;
    private JPanel menuMainPanel,buttonPanel;
    private ImageIcon bgImage,kitchenIcon;
    private JButton button1,button2,button3,button4;
    private ActionListener option1,option2,option3,option4;
    public MainMenu(String username){
        this.username=username;
        currentMenuOpen=0;
        showMenu();
        orderMenu = new OrderMenu();
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
        ImageIcon image = new ImageIcon("Project Folder/data/Images/fxiconkitchen.png");
        Image img = image.getImage();
        setIconImage(img);
        welcomeLabel = new JLabel("Welcome "+username);
        selectOptionLabel = new JLabel("Please select an option above");
        selectOptionLabel.setFont(new Font("Ariel",1,24));
        welcomeLabel.setFont(new Font("Ariel",1,24));
        kitchenIcon = new ImageIcon("data/Images/kitchenicon.png");
        kitchenLabel = new JLabel(addImages(kitchenIcon,1,1));

        mainMenuPanelAttributes();
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        //background.add(kitchenLabel,gbc);
        menuMainPanel.add(kitchenLabel,gbc);
        gbc.gridy = 1;
        //background.add(welcomeLabel,gbc);
        menuMainPanel.add(welcomeLabel,gbc);
        gbc.gridy = 2;
        menuMainPanel.add(selectOptionLabel,gbc);
        background.add(menuMainPanel);
        menuMainPanel.repaint();
       // background.add(selectOptionLabel,gbc);
    }
    private void mainMenuPanelAttributes(){
        menuMainPanel = new JPanel();
        menuMainPanel.setLayout(new GridBagLayout());
        menuMainPanel.setOpaque(false);
    }
    private void menuOptions() {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        button1 = new JButton("Kitchen Orders");
        button2 = new JButton("Kitchen.Menu Management");
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
    private void hideButtons(){
//        button1.setVisible(false);
//        button2.setVisible(false);
//        button3.setVisible(false);
//        button4.setVisible(false);
        kitchenLabel.setVisible(false);
        selectOptionLabel.setVisible(false);
        welcomeLabel.setVisible(false);
    }
    private void showButtons(){
        kitchenLabel.setVisible(true);
        selectOptionLabel.setVisible(true);
        welcomeLabel.setVisible(true);
    }

    private void mouseListener(){
        option1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeMenu(1);
                currentMenuOpen=1;
            }
        };
        option2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMenuOpen=2;
                changeMenu(2);
            }
        };
        option3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMenuOpen=3;
                changeMenu(3);

            }
        };
        option4 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMenuOpen=4;
                changeMenu(4);
            }
        };
    }

    private void changeMenu(int menuID){
        switch (menuID){
            case 1:
                if(currentMenuOpen==1){
                    break;
                }
                System.out.println("Show the Tickets");
                menuMainPanel.setVisible(false);
                background.setLayout(new BorderLayout());
                background.add(orderMenu,BorderLayout.WEST);
                orderMenu.setVisible(true);


                currentMenuOpen=1;
                break;
            case 2:
                System.out.println("Show the Kitchen.Menu Management System");
                break;
            case 3:
                System.out.println("Show the Waste Management System");
                break;
            case 4:
                System.out.println("KitchenGUI.Main Kitchen.Menu");
                menuMainPanel.setVisible(true);
                if(orderMenu!= null){
                    orderMenu.setVisible(false);
                }
        }
    }
}
