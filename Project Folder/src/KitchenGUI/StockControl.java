package KitchenGUI;

import DatabaseConnection.databaseAdmin;
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
import java.util.ArrayList;
import java.util.List;

public class StockControl extends JPanel {
    private PreparedStatement searchState;
    private  InventoryManagement inventoryManagement;
    private Connection con;
    private JScrollPane scrollPane;
    private Font abrilFont;
    private MigLayout migLayout,mig2;
    private int currentMenu;
    private createMenu creatingMenu;
    private JPanel scrollInto;
    private JTextField ingIDField;
    private JTextField ingNameField;
    private JTextField inputID;
    private JButton searchButton,updateButton;
    private databaseAdmin database;
    private List<JLabel> currentLabels;
    private JTextField setAmountField;

    public StockControl(){

        currentLabels = new ArrayList<>();
        addFont();
        initialLayout();
        addButtons();

        addSearches();
        scrollPane();
        addButtonListener();


    }

    private void search(){
        searchState=null;
        for (JLabel s : currentLabels){
            scrollInto.remove(s);

        }
        repaint();
        revalidate();
        String ingredientName = ingNameField.getText();
        String ingredientID = ingIDField.getText();
        int waste = 0;
        int quantity = 0;
        try {
            String sql = "SELECT * FROM Ingredient WHERE";

            if (!ingredientName.isEmpty()){
                sql+=" Name=?";
                searchState = con.prepareStatement(sql);
                searchState.setString(1,ingredientName);


            }
            else if (!ingredientID.isEmpty()){
                sql+=" ID=?";
                searchState = con.prepareStatement(sql);
                searchState.setString(1,ingredientID);

            }

            ResultSet searchSet = searchState.executeQuery();
            while(searchSet.next()){
                ingredientName = searchSet.getString(2);
                ingredientID = String.valueOf(searchSet.getInt(1));
                quantity = searchSet.getInt(3);
                waste = searchSet.getInt(4);
                addIngredientsToPane(ingredientID,ingredientName,quantity,waste);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }



    private void addButtonListener(){
        ActionListener buttonListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        };

        ActionListener updateListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        };
        updateButton.addActionListener(updateListener);
        searchButton.addActionListener(buttonListen);
    }

    private void update(){
        int amount = Integer.parseInt(setAmountField.getText());
        int ID = Integer.parseInt(inputID.getText());
        try{
            PreparedStatement statement = con.prepareStatement("UPDATE Ingredient SET AvailableQuantity=? WHERE ID=?");
            statement.setInt(1,amount);
            statement.setInt(2,ID);
            statement.executeUpdate();
            searchState=null;
            for (JLabel s : currentLabels){
                scrollInto.remove(s);

            }
            scrollPaneContents(true);

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }



    }

    private void addSearches(){

        JLabel IngredientNameLabel = new JLabel("Ingredient Name");
        JLabel IngredientIDLabel = new JLabel("Ingredient ID");
        JLabel inputIDLabel = new JLabel("Input ID");
        inputID = new JTextField(5);


        ingNameField = new JTextField(10);
        ingIDField = new JTextField(10);
        searchButton = new JButton("Search");

        JLabel setAmount = new JLabel("Set Quantity: ");
        setAmountField = new JTextField(5);
        updateButton = new JButton("Update");

        add(IngredientNameLabel);
        add(IngredientIDLabel);

        add(inputIDLabel);
        add(inputID);
        add(setAmount);
        add(setAmountField);
        add(updateButton,"wrap");
        add(ingNameField);
        add(ingIDField);
        add(searchButton,"wrap");


    }
    private void scrollPane(){
        scrollInto = new JPanel();
        mig2 = new MigLayout();
        scrollInto.setLayout(mig2);
        scrollPane = new JScrollPane(scrollInto);

        add(scrollPane);
        scrollPaneContents(false);

    }

    private void scrollPaneContents(Boolean bool){
        databaseAdmin database = null;
        int ID;
        String Name;
        int quantity;
        int waste;
        if (!bool){
            JLabel wasteLabel = new JLabel("Waste");
            JLabel idLabel = new JLabel("Ingredient ID");
            JLabel nameLabel = new JLabel("Ingredient Name");
            JLabel quantityLabel = new JLabel("Quantity");

            scrollInto.add(idLabel);
            scrollInto.add(nameLabel);
            scrollInto.add(quantityLabel);
            scrollInto.add(wasteLabel,"wrap");
        }

        try {
            database = new databaseAdmin();
            con = database.returnConnection();
            PreparedStatement getIngredients = con.prepareStatement("SELECT * FROM Ingredient");
            ResultSet ingrResults = getIngredients.executeQuery();
            while(ingrResults.next()){
                ID = ingrResults.getInt(1);
                Name = ingrResults.getString(2);
                quantity = ingrResults.getInt(3);
                waste = ingrResults.getInt(4);
                addIngredientsToPane(String.valueOf(ID),Name,quantity,waste);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void addIngredientsToPane(String ID,String Name,int Quantity,int Waste){
        JLabel ingredientID = new JLabel(String.valueOf(ID));
        JLabel ingredientName = new JLabel(Name);
        JLabel quantity = new JLabel(String.valueOf(Quantity));
        JLabel waste = new JLabel(String.valueOf(Waste));
        currentLabels.add(ingredientID);
        currentLabels.add(ingredientName);
        currentLabels.add(quantity);
        currentLabels.add(waste);
        if (Quantity < 20){
            quantity.setForeground(Color.red);
        }
        scrollInto.add(ingredientID);
        scrollInto.add(ingredientName);
        scrollInto.add(quantity);
        scrollInto.add(waste,"wrap");

        repaint();
        revalidate();
    }


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

    private void initialLayout(){
        migLayout = new MigLayout("align center,alignx center");
        setLayout(migLayout);
        setOpaque(true);
        setBackground(Color.decode("#3d4547"));
    }
    private void addButtons(){
        JLabel StockControlLabel = new JLabel("Stock Control");


        StockControlLabel.setForeground(Color.decode("#A9A8A6"));


        StockControlLabel.setBackground(Color.decode("#242b2e"));

        StockControlLabel.setFont(abrilFont.deriveFont(Font.PLAIN,20));


        add(StockControlLabel,"wrap");
        repaint();
        revalidate();
    }
}
