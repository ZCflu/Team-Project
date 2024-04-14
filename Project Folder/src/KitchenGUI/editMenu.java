package KitchenGUI;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class editMenu extends JPanel {
    private Font abrilFont;
    private MigLayout migLayout;
    private int currentMenu;
    private createMenu creatingMenu;
    public editMenu(){
        creatingMenu = new createMenu();
        addFont();
        initialLayout();
        addButtons();
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
        JButton button1 = new JButton("New Menu");
        JButton button2 = new JButton("Update Menus");

        button1.setForeground(Color.decode("#A9A8A6"));
        button2.setForeground(Color.decode("#A9A8A6"));

        button1.setFocusPainted(false);
        button2.setFocusPainted(false);

        button1.setBackground(Color.decode("#242b2e"));
        button2.setBackground(Color.decode("#242b2e"));

        button1.setFont(abrilFont.deriveFont(Font.PLAIN,20));
        button2.setFont(abrilFont.deriveFont(Font.PLAIN,20));

        ActionListener button1Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMenu=1;
                add(creatingMenu,"wrap,grow,alignx leading");
                creatingMenu.show();
                repaint();
                revalidate();
                System.out.println("Creating a new menu");
            }
        };

        ActionListener button2Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMenu=2;
                System.out.println("Editing Menus");
            }
        };

        button1.addActionListener(button1Listener);
        button2.addActionListener(button2Listener);

        add(button1);
        add(button2,"wrap");
        repaint();
        revalidate();
    }
}
