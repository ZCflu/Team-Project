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

/**
 * Class for viewing stock control information. Contains data from the Kitchen database. Allowing you to search for a specific ingredient by name or ID, as well as updating the stock in the Kitchen database.
 */
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

    /**
     * Constructor that initializes a new array list (currentLabels) that keeps track of the current ingredients shown on screen. Executes the addFont(), initialLayout(),addButtons(),addSearched(),scrollPane(),addButtonListeners() methods.
     */
    public StockControl(){

        currentLabels = new ArrayList<>();
        addFont();
        initialLayout();
        addButtons();

        addSearches();
        scrollPane();
        addButtonListener();


    }

    /**
     * Method that allows searching for a specific ingredient by name or by ID. Connects to the Kitchen database, and uses the data input in the JTextField objects ingNameField and inputID. Removes the labels labels that contain other ingredients before showing the searched ingredient.
     */
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


    /**
     * Method to add ActionListeners to the updateButton and searchButton.
     * The updateButton will execute the update() function.
     * The searchButton will execute the search() function.
     */
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

    /**
     * Method to update the amount of stock for the input ingredient ID. Uses a connection to the database to execute an update query on the AvailableQuantity in the Ingredient table where the ID is the number input in the inputID JTextField.
     * Executes the scrollPaneContents method with a true parameter.
     */
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


/**
 * Method to add the search fields, allowing the user to search for an ingredient by the name or it's ID.
 */
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

    /**
     * Method that initialises a new JScrollPane and JPanel, allowing overflow of the ingredients to be handled. A new MigLayout is created, and set as the layout for the JPanel. The JScrollPane is then added to the JPanel.
     * scrollPaneContents is then executed with a false parameter.
     */
    private void scrollPane(){
        scrollInto = new JPanel();
        mig2 = new MigLayout();
        scrollInto.setLayout(mig2);
        scrollPane = new JScrollPane(scrollInto);

        add(scrollPane);
        scrollPaneContents(false);

    }

    /**
     * Method to retrieve the ingredients from the Kitchen database and present them on the JPanel.
     * If called from the scrollPane() function, the labels outlining the different details of the ingredient are created. A database connection is then made to retrieve the full list of ingredients in the Kitchen's database. For each ingredient, the addIngredientsToPane method is called.
     * If called from the update() function, a new database connection is made to retrieve the updated list of ingredients in the Kitchen's database. For each ingredient, the addIngredientsToPane method is called.
     *
     * @see databaseAdmin
     * @param bool To check whether the call of the method is coming from the method scrollPane() or search().
     */
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

    /**
     * Method to add ingredients found from the search() method or scrollPaneContents() method. Creates labels for each attribute of the ingredient (ID,Name,Quantity, and Waste) and adds the labels to the scrollInto pane.
     * Contains a check to see whether the quantity of the ingredient is below a certain threshhold (default: 20), if true, the colour of the quantity text is set to (default: red).
     * @param ID Unique identifier of the ingredient.
     * @param Name Name of the ingredient.
     * @param Quantity Quantity of the ingredient.
     * @param Waste Amount of waste for the ingredient.
     */
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

    /**
     * Method to add the Lancaster's logo font to the class so it can be used in the buttons.
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
     * Method to set the layout of the StockControl panel. Background colour, opaque boolean is set.
     * @see MigLayout
     */
    private void initialLayout(){
        migLayout = new MigLayout("align center,alignx center");
        setLayout(migLayout);
        setOpaque(true);
        setBackground(Color.decode("#3d4547"));
    }

    /**
     * Method to add the of "Stock Control" at the top of the screen. Attributes of the label are set, including: Foreground and background colour, and Font.
     */
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
