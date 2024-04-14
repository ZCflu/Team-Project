package KitchenGUI;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MenuManagement extends JPanel {
    private BorderLayout borderLay;
    private JButton viewMenus;
    private JButton createMenu;
    private JButton createDish;
    private JButton viewDishes;
    private Font abrilFont;
    public MenuManagement(){
        addFont();
        initialLayout();
        features();
        addTopMenu();

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
        borderLay = new BorderLayout();
    }

    private void features(){
        setBackground(Color.decode("#3d4547"));

    }

    private void addTopMenu(){
        JPanel topMenu= new JPanel();
        topMenu.setBackground(Color.decode("#3d4547"));
        MigLayout migLay = new MigLayout();
        topMenu.setLayout(migLay);

        viewMenus = new JButton("View Menus");
        createMenu = new JButton("Create Menu");
        createDish = new JButton("Create Dish");
        viewDishes = new JButton("View Dishes");

        viewMenus.setForeground(Color.decode("#A9A8A6"));
        createMenu.setForeground(Color.decode("#A9A8A6"));
        createDish.setForeground(Color.decode("#A9A8A6"));
        viewDishes.setForeground(Color.decode("#A9A8A6"));

        viewMenus.setFocusPainted(false);
        createMenu.setFocusPainted(false);
        createDish.setFocusPainted(false);
        viewDishes.setFocusPainted(false);

        viewMenus.setBackground(Color.decode("#242b2e"));
        createMenu.setBackground(Color.decode("#242b2e"));
        createDish.setBackground(Color.decode("#242b2e"));
        viewDishes.setBackground(Color.decode("#242b2e"));

        viewMenus.setFont(abrilFont.deriveFont(Font.PLAIN,20));
        createDish.setFont(abrilFont.deriveFont(Font.PLAIN,20));
        createMenu.setFont(abrilFont.deriveFont(Font.PLAIN,20));
        viewDishes.setFont(abrilFont.deriveFont(Font.PLAIN,20));

        addListeners();

        topMenu.add(viewMenus);
        topMenu.add(createMenu);
        topMenu.add(createDish);
        topMenu.add(viewDishes);



        add(topMenu,BorderLayout.NORTH);




    }

    private void addListeners(){
        ActionListener viewMenListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Viewing Menu's");

            }
        };
        ActionListener createMenListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Creating Menus");

            }
        };
        ActionListener createDishListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Creating Dish");

            }
        };
        ActionListener viewDishListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Viewing Dishes");

            }
        };
        viewMenus.addActionListener(viewMenListener);
        createMenu.addActionListener(createMenListener);
        createDish.addActionListener(createDishListener);
        viewDishes.addActionListener(viewDishListener);




    }



}
