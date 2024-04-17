package KitchenGUI;

import DatabaseConnection.databaseAdmin;
import Kitchen.Menu;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Class that allows the viewing of menu's stored in the Kitchen database.
 */
public class viewMenus extends JPanel {
    private MigLayout mig;
    private JPanel menus;

    private int menuID;
    private int creationDate;
    private int chefApprovalDate;
    private Font abrilFont;

    /**
     * Constructor that executes features(), addFont(),menuPanel(),showMenus().
     */
    public viewMenus(){
        features();
        addFont();
        menuPanel();

        showMenus();
    }

    /**
     * Method to create a new JPanel for the menu's to appear on. Sets the background colour to a similar palette to that of the Lancaster's logo. Sets the layout to a MigLayout as well as a JScrollPane to handle overflow of menus.
     */
    private void menuPanel(){
        menus = new JPanel();
        menus.setOpaque(true);
        menus.setBackground(Color.decode("#4d5b60"));
        menus.repaint();
        setOpaque(true);
        setBackground(Color.decode("#4d5b60"));
        menus.setLayout(new MigLayout());
        add(new JScrollPane(menus), BorderLayout.NORTH);


    }

    /**
     * Method that shows the menu's in the Kitchen database on the menus JPanel. Starts a new connection to the database to retrieve the menu and the details such as the menuID, creationDate, and chefApprovalDate.
     * The menu is then added to menus JPanel by using the createAddPanel() method.
     * @see databaseAdmin
     */
    private void showMenus(){
        databaseAdmin database = null;
        try {
            database = new databaseAdmin();
            Connection con = database.returnConnection();
            PreparedStatement getMenus = con.prepareStatement("SELECT * FROM Menu");
            ResultSet menuResults = getMenus.executeQuery();
            while(menuResults.next()){
                menuID=menuResults.getInt(1);
                creationDate = menuResults.getInt(2);
                chefApprovalDate = menuResults.getInt(3);
                createAddPanel(menuID,creationDate,chefApprovalDate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Method to create a new JPanel for each menu found in the database. Details of the menu are given a respective JLabel. A button to review the dishes of the menu is added using the addButtonToDetails() method.
     * @param menuID Unique identifer for the menu.
     * @param creationDate Creation date of the menu.
     * @param chefApprovalDate Date the head and sous chef approved the menu.
     */
    private void createAddPanel(int menuID,int creationDate,int chefApprovalDate){
        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(true);
        menuPanel.setBackground(Color.decode("#4d5b60"));
        JLabel label = new JLabel("Menu ID: "+menuID);
        JLabel label2 = new JLabel("Creation Date: "+creationDate);
        JLabel label3 = new JLabel("Chef Approval Date: "+chefApprovalDate);
        label.setFont(new Font("Verdana",Font.BOLD,15));
        label2.setFont(new Font("Verdana",Font.BOLD,15));
        label3.setFont(new Font("Verdana",Font.BOLD,15));

        label.setForeground(Color.white);
        label2.setForeground(Color.white);
        label3.setForeground(Color.white);
        menuPanel.add(label,"wrap,grow,newline 10");
        menuPanel.add(label2,"wrap,grow,newline 10");
        menuPanel.add(label3,"wrap,grow,newline 10");

        addButtonToDetails(menuPanel,menuID);

        menus.add(menuPanel,"wrap");
        menus.repaint();
    }

    /**
     * Method to create and add a See Details button to view the dishes of a menu. Contains a new ActionListener that creates a new MenuDishDetails object, putting in the menuID as a parameter.
     * @param panel The panel the button will be on.
     * @param menuID Unique identifier for the menu used for the creation of MenuDishDetails object.
     */

    private void addButtonToDetails(JPanel panel,int menuID){
        JButton seeDetails = new JButton("See Details");
        ActionListener detailsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuDishDetails seeDeets = new MenuDishDetails(menuID);
                seeDeets.show();
            }
        };
        seeDetails.addActionListener(detailsListener);
        panel.add(seeDetails,"wrap,grow,newline 10");


    }

    /**
     * Method to add the Lancaster's Logo font so that it can be used in the buttons in the class.
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
     * Method to set the attributes of the JPanel. Uses a MigLayout and sets the background colour similarly to the colour of the Lancaster's Logo.
     * @see MigLayout
     */
    private void features(){
        mig = new MigLayout();
        setLayout(mig);
        setOpaque(true);
        setBackground(Color.decode("#2B3336"));
       // setBackground(Color.decode("#3d4547"));

    }
}
