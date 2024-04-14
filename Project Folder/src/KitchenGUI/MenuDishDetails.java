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

public class MenuDishDetails extends JFrame {
    private int menuID;
    private JPanel jPanel;
    private BorderLayout borderLayout;
    private MigLayout migLayout;

    public MenuDishDetails(int MenuID) {
        this.menuID = MenuID;
        setAttributes();
        addLayout();
        addHeader();
        addPanel();
    }

    private void setAttributes() {
        setSize(500, 500);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Added to ensure the window closes properly
    }

    private void addPanel() {
        add(jPanel);
    }

    private void addLayout() {
        jPanel = new JPanel();
        jPanel.setBackground(Color.decode("#2B3336"));
        migLayout = new MigLayout();
        borderLayout = new BorderLayout();
        jPanel.setLayout(migLayout); // Set the layout for jPanel
        setLayout(borderLayout);
    }

    private void addHeader() {
        JLabel dishLabel = new JLabel("Dishes: ");
        dishLabel.setForeground(Color.white);
        jPanel.add(dishLabel, "wrap"); // Add "wrap" to move to the next row
        getDishes();
        jPanel.repaint();
        jPanel.revalidate();
    }

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
