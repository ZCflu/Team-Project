package KitchenGUI;

import KitchenGUI.GUIMenu;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Class that extends GUIMenu. Adds the left selection menu on the JFrame, allowing the user to select between different areas of the software.
 * @see GUIMenu
 */

public class MainMenu extends GUIMenu {
    private JScrollPane jScrollPane;
    private StockControl stockControl;
    private String username;
    private JPanel menuPanel;
    private MigLayout mig;
    private int currentMenuOpen;
    private OrderMenu orderMenu;
    private MenuManagement menuManagement;
    private JLabel kitchenLabel,welcomeLabel,selectOptionLabel;
    private JPanel menuMainPanel,buttonPanel;
    private ImageIcon bgImage,kitchenIcon;
    private JButton button1,button2,button3,button4;
    private ActionListener homeOption,ticketsOption,menuOption,stockOption,wasteOption,exitOption;
    private Font abrilFont;

    /**
     * Constructor for the class. Initialises new stockControl(), orderMenu, and menuManagement objects. Executes the addFont() and migLay methods.
     * @param username Used to show the name of the user on screen.
     */
    public MainMenu(String username){
        stockControl = new StockControl();
        orderMenu = new OrderMenu();
        menuManagement = new MenuManagement();


        this.username=username;
        currentMenuOpen=0;
        addFont();
        //showMenu();
        migLay();

    }

    /**
     * Method to add the Lancaster's logo font to the class.
     */
    private void addFont(){
        try {
            abrilFont = Font.createFont(Font.TRUETYPE_FONT, new File("Project Folder/data/Fonts/AbrilFatface-Regular.otf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(abrilFont);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Method to a new JScrollPane, a JPanel and initialise a new MigLayout. The JScrollPane is added to the class' JFrame. The JPanel is added to the JScrollPane. Welcoming label, the Lancaster's logo, and buttons are added to the JPanel.
     * Executes the migLayButtons() method.
     * @see MigLayout
     */
    private void migLay(){
        mig = new MigLayout();
        menuPanel = new JPanel();
        jScrollPane = new JScrollPane(menuPanel);
        menuPanel.setLayout(mig);
        menuPanel.setOpaque(true);
        menuPanel.setBackground(Color.decode("#2B3336"));

        JLabel userName = new JLabel("Welcome "+username);
        menuPanel.add(userName,"wrap");

        ImageIcon logo = new ImageIcon("Project Folder/data/Images/lancasterlogo.jpeg");
        menuPanel.add(new JLabel(addImages(logo,4,4)),"wrap");
        migLayButtons();
        add(jScrollPane,BorderLayout.WEST);
        //add(menuPanel,BorderLayout.WEST);
        repaint();
        show();
    }

    /**
     * Method to create the buttons and set the attributes of the buttons. The text, foregound colour, background colour, setFocusPainted and font is added.
     * Executed the mouseListener method, putting in the buttons created as parameters.
     * Sets the size of the buttons.
     * Adds the buttons to the JPanel.
     */
    private void migLayButtons(){
        JButton HomeButton = new JButton("Home");
        JButton TicketsButton = new JButton("Tickets");
        JButton MenuManButton = new JButton("Menu Management");
        JButton StockButton = new JButton("Stock Control");
        JButton WasteButton = new JButton("Waste Control");
        JButton ExitButton = new JButton("Exit");

        HomeButton.setForeground(Color.decode("#A9A8A6"));
        TicketsButton.setForeground(Color.decode("#A9A8A6"));
        MenuManButton.setForeground(Color.decode("#A9A8A6"));
        StockButton.setForeground(Color.decode("#A9A8A6"));
        WasteButton.setForeground(Color.decode("#A9A8A6"));
        ExitButton.setForeground(Color.decode("#A9A8A6"));

        HomeButton.setFocusPainted(false);
        TicketsButton.setFocusPainted(false);
        MenuManButton.setFocusPainted(false);
        StockButton.setFocusPainted(false);
        WasteButton.setFocusPainted(false);
        ExitButton.setFocusPainted(false);

        HomeButton.setBackground(Color.decode("#242b2e"));
        TicketsButton.setBackground(Color.decode("#242b2e"));
        MenuManButton.setBackground(Color.decode("#242b2e"));
        StockButton.setBackground(Color.decode("#242b2e"));
        WasteButton.setBackground(Color.decode("#242b2e"));
        ExitButton.setBackground(Color.decode("#242b2e"));


        HomeButton.setFont(abrilFont.deriveFont(Font.PLAIN,20));
        TicketsButton.setFont(abrilFont.deriveFont(Font.PLAIN,20));
        MenuManButton.setFont(abrilFont.deriveFont(Font.PLAIN,20));
        StockButton.setFont(abrilFont.deriveFont(Font.PLAIN,20));
        WasteButton.setFont(abrilFont.deriveFont(Font.PLAIN,20));
        ExitButton.setFont(abrilFont.deriveFont(Font.PLAIN,20));

        mouseListener(HomeButton,TicketsButton,MenuManButton,StockButton,WasteButton,ExitButton);


        Dimension buttonSize = new Dimension(100,200);
        HomeButton.setSize(buttonSize);
        menuPanel.add(HomeButton,"wrap 2,grow,newline 50,pushy 50");
        menuPanel.add(TicketsButton,"wrap 2,grow,newline 50,pushy 50");
        menuPanel.add(MenuManButton,"wrap 2,grow,newline 50,pushy 50");
        menuPanel.add(StockButton,"wrap 2,grow,newline 50,pushy 50");
        menuPanel.add(WasteButton,"wrap 2,grow,newline 50,pushy 50");
        menuPanel.add(ExitButton,"wrap 2,grow,newline 50,pushy 50");

    }

    /**
     * Method that creats ActionListeners for each of the buttons on the menu. Each listener executes the removeCurrentMenu() method and changeMenu() method. It then sets the currentMenuOpen to the respective value. Adds ActionListeners to the buttons.
     * @param homeButton JButton created from migLayButtons() method.
     * @param ticketsButton JButton created from migLayButtons() method.
     * @param menuManagement JButton created from migLayButtons() method.
     * @param stockButton JButton created from migLayButtons() method.
     * @param wasteButton JButton created from migLayButtons() method.
     * @param exitButton JButton created from migLayButtons() method.
     */

    private void mouseListener(JButton homeButton,JButton ticketsButton,JButton menuManagement,JButton stockButton,JButton wasteButton,JButton exitButton){
        homeOption = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCurrentMenu();
                changeMenu(1);
                currentMenuOpen=1;
            }
        };
       ticketsOption = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCurrentMenu();
                currentMenuOpen=2;
                changeMenu(2);
            }
        };
        menuOption = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCurrentMenu();
                currentMenuOpen=3;
                changeMenu(3);

            }
        };
        stockOption = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                removeCurrentMenu();
                currentMenuOpen=4;
                changeMenu(4);
            }
        };
        wasteOption = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCurrentMenu();
                currentMenuOpen=5;
                changeMenu(5);
            }
        };
        exitOption = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCurrentMenu();
                currentMenuOpen=6;
                changeMenu(6);
            }
        };
        homeButton.addActionListener(homeOption);
        ticketsButton.addActionListener(ticketsOption);
        menuManagement.addActionListener(menuOption);
        stockButton.addActionListener(stockOption);
        wasteButton.addActionListener(wasteOption);
        exitButton.addActionListener(exitOption);
    }

    /**
     * Method that changes the current menu on the JFrame. Contains a switch case adding the selected menu to the frame, taking in the menuID from the mouseListener() function.
     * @param menuID Menu identifier.
     */

    private void changeMenu(int menuID){
        switch (menuID){
            case 1:
                System.out.println("Home Button");

                break;
            case 2:
                System.out.println("Show the Tickets");
                //background.setLayout(new BorderLayout());
                add(orderMenu,BorderLayout.CENTER);
                revalidate();
                repaint();
                orderMenu.setVisible(true);
                break;
            case 3:
                System.out.println("Show the Menu Management System");
                add(menuManagement,BorderLayout.CENTER);
                revalidate();
                repaint();
                menuManagement.setVisible(true);
                break;
            case 4:
                System.out.println("Show the Stock Control System");
                //add(stockControl,BorderLayout.CENTER);
                // ADD YOUR INVENTORY MANAGEMENT HERE!
                add(stockControl,BorderLayout.CENTER);
                revalidate();
                repaint();
                stockControl.setVisible(true);
                break;
            case 5:
                break;
            case 6:
                System.exit(0);
                break;

        }
    }

    /**
     * Method to remove the current menu shown on the JFrame. Setting the visibility to false if a different menu is selected.
     */

    private void removeCurrentMenu(){
        System.out.println(currentMenuOpen);
        switch (currentMenuOpen){
            case 1:
                System.out.println("gets here?");
                orderMenu.setVisible(false);
                menuManagement.setVisible(false);
                break;
            case 2:
                orderMenu.setVisible(false);
                break;
            case 3:
                menuManagement.setVisible(false);
                break;
            case 4:
                stockControl.setVisible(false);
                break;


        }
        repaint();
        revalidate();


    }
}
