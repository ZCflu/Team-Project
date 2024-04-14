package KitchenGUI;

import DatabaseConnection.databaseAdmin;
import Kitchen.Dish;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;

public class createMenu extends JPanel {
    private Map<String,Integer> dishesMap;
    private HashSet<Integer> dishIDs;
    private List<JLabel> addedLabels;

    private Font abrilFont;
    private JButton createMenuButton,addDishButton;
    private MigLayout migLayout;
    private JLabel selectedDishes;
    private JComboBox<String> comboBox;
    private int column;
    public createMenu(){
        column=0;
        addedLabels = new ArrayList<>();
        dishIDs = new HashSet<>();
        dishesMap= new HashMap<>();
        addFont();
        initialLayout();
        labels();
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
//        setBackground(Color.decode("#4d5b60"));
        setBackground(Color.white);
    }

    private void labels(){
        JLabel creatingMenu = new JLabel("Creating Menu");
        add(creatingMenu,"wrap");
        addDropdown(false);
        addButtons();
        createButtonListener();
    }

    private void addDropdown(boolean bool){
        List<String> options = new ArrayList<>();
        try {
            databaseAdmin database = new databaseAdmin();
            Connection con = database.returnConnection();
            PreparedStatement dishStatement = con.prepareStatement("SELECT * FROM Dish");
            ResultSet dishes = dishStatement.executeQuery();

            while(dishes.next()){
                String dishName = dishes.getString(2);
                int dishID = dishes.getInt(1);
                dishesMap.put(dishName,dishID);
                options.add(dishName);
            }

            String[] array = options.toArray(new String[0]); // Convert list to array

            comboBox = new JComboBox<>(array); // Create JComboBox with the array of options
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (bool){
            add(comboBox,"grow,wrap");
        }
        else{
            add(comboBox,"grow");
        }

    }

    private void addButtons(){
        addDishButton = new JButton("Add Dish");
        createMenuButton = new JButton("Create Menu");
        add(addDishButton,"wrap");

        add(createMenuButton,"wrap");
        listDishesLabel();

    }
    private void listDishesLabel(){
        JLabel selectedDishes = new JLabel("Selected Dishes: ");
        add(selectedDishes,"wrap");
    }

    private void addNewDish(String dishName){
        JLabel dish = new JLabel(dishName);
        addedLabels.add(dish);
        dishIDs.add(dishesMap.get(dishName));
        if(column!=5){
            add(dish);
            column++;
        }
        else{
            add(dish,"wrap");
            column=0;
        }
        repaint();
        revalidate();
    }
    private void createButtonListener(){
        ActionListener addDishListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Adding Dish");
                addNewDish((String) comboBox.getSelectedItem());
            }
        };
        ActionListener createMenuListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Creating Menu");
                submitMenu();
            }
        };
        ActionListener removeDishListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Removing Dish");
            }
        };
            addDishButton.addActionListener(addDishListen);
            createMenuButton.addActionListener(createMenuListen);



    }

    private void submitMenu() {
        int generatedID=0;
        databaseAdmin database = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String creationDate = dateFormat.format(new Date());
        int creationDateINT = Integer.parseInt(creationDate);
        try {
            database = new databaseAdmin();
            Connection con = database.returnConnection();
            PreparedStatement dishStatement = con.prepareStatement(
                    "INSERT INTO Menu (`Creationdate`, `ChefApprovalDate`) VALUES (?, '00000')",
                    Statement.RETURN_GENERATED_KEYS); // Specify RETURN_GENERATED_KEYS here
            dishStatement.setInt(1, creationDateINT);
            dishStatement.executeUpdate();

            // Retrieve the automatically generated ID
            ResultSet generatedKeys = dishStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                generatedID = generatedKeys.getInt(1);
                System.out.println("Generated ID: " + generatedID);
            }
            for(int i : dishIDs){
                dishStatement = con.prepareStatement("INSERT INTO MenuDish (`MenuID`, `DishID`) VALUES (?, ?)");
                dishStatement.setInt(1,generatedID);
                dishStatement.setInt(2,i);
                dishStatement.executeUpdate();
            }

        dishIDs.clear();
        for(JLabel d : addedLabels){
            remove(d);
        }
        repaint();
        revalidate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
