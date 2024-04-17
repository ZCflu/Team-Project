package KitchenGUI;

import DatabaseConnection.databaseAdmin;
import Kitchen.Dish;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that displays the dishes of the selected menu. A new JFrame is opened with the details of the Dish IDs, Dish Names, Dish Types, Dish RecipeIDs and Availability of the dish.
 */
public class MenuDishDetails extends JFrame {
    private int menuID;
    private JPanel jPanel;
    private JScrollPane jScrollPane;
    private BorderLayout borderLayout;
    private MigLayout migLayout;

    /**
     * Constructor that executes the setAttributes(), addLayout(),addHeader(),addPanel() methods.
     * @param MenuID Used in the SQL in getDishes() to generate the dishes in the selected menu.
     */
    public MenuDishDetails(int MenuID) {
        this.menuID = MenuID;
        setAttributes();
        addLayout();
        addHeader();
        addPanel();
    }

    /**
     * Method to set the attributes of the JFrame. The size is set to a standard size of 500 in width and height. The relative location of the frame is then set to null so it appears in the middle of the screen.
     */
    private void setAttributes() {
        setSize(500, 500);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Added to ensure the window closes properly
    }

    /**
     * Method to add a JScrollPane to the Frame. Allowing scrolling if there is overflow of the dishes.
     */
    private void addPanel() {
        //add(jPanel);
        add(jScrollPane);
    }

    /**
     * Method to create the JPanel, JScrollPane. Sets the JPanel layout to the MigLayout and the Frame layout to a BorderLayout.
     * @see MigLayout
     */
    private void addLayout() {
        jPanel = new JPanel();
        jScrollPane = new JScrollPane(jPanel);
        jPanel.setBackground(Color.decode("#2B3336"));
        migLayout = new MigLayout();
        borderLayout = new BorderLayout();
        jPanel.setLayout(migLayout); // Set the layout for jPanel
        setLayout(borderLayout);
    }

    /**
     * Method to adds details before dishes are printed onto the JPanel. A label is created that outlines the dishes are about to follow. The foreground colour is selected.
     * The getDishes() method is then called to generate the dishes.
     */
    private void addHeader() {
        JLabel dishLabel = new JLabel("Dishes: ");
        dishLabel.setForeground(Color.white);
        jPanel.add(dishLabel, "wrap"); // Add "wrap" to move to the next row
        getDishes();
        jPanel.repaint();
        jPanel.revalidate();
    }

    /**
     * Method to retrieve the dishes that the menu contains from the Kitchen database. Starts a connection to the Kitchen database using the databaseAdmin class.
     * The details of each dish is added to a respective JLabel, then added to the JPanel.
     */
    private void getDishes(){
            String dishName=null;
            String dishType=null;
            int recipeID=0;
            int availability=0;
            int dishID=0;
        try {
            databaseAdmin database = new databaseAdmin();
            Connection con = database.returnConnection();
            PreparedStatement dishStatement = con.prepareStatement("SELECT * FROM MenuDish WHERE MenuID=?");
            dishStatement.setInt(1,menuID);
            ResultSet dishSet = dishStatement.executeQuery();

            while(dishSet.next()){
                dishID = dishSet.getInt(2);
                PreparedStatement dishDetails = con.prepareStatement("SELECT * FROM Dish WHERE ID=?");
                dishDetails.setInt(1,dishID);
                ResultSet details = dishDetails.executeQuery();
                while(details.next()){
                    dishName = details.getString(2);
                    dishType = details.getString(3);
                    recipeID = details.getInt(4);
                    availability = details.getInt(5);
                }
                JLabel dishid = new JLabel("Dish ID: "+dishID);
                JLabel name = new JLabel("Dish Name: "+dishName);
                JLabel type = new JLabel("Dish Type: "+dishType);
                JLabel id = new JLabel("Recipe ID: "+String.valueOf(recipeID));
                JLabel avail = new JLabel("Availability: "+String.valueOf(availability));
                dishid.setForeground(Color.white);
                name.setForeground(Color.white);
                type.setForeground(Color.white);
                id.setForeground(Color.white);
                avail.setForeground(Color.white);

                jPanel.add(dishid,"wrap,newline 15");
                jPanel.add(name, "wrap");
                jPanel.add(type, "wrap");
                jPanel.add(id, "wrap");
                jPanel.add(avail, "wrap");

            }





        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
