package KitchenGUI;

import DatabaseConnection.databaseDataCon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Authentication extends JFrame {
    private JPanel panel;
    private JButton loginButton;
    private JTextField userField,passField;
    private scaleImage scaler;
    private GridBagConstraints gbc;
    private JLabel incorrect;
    public Authentication(){
        scaler = new scaleImage();
        setAttributes();
        addPanel();
        show();

    }

    private void setAttributes(){
        setSize(800,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void addPanel(){
        panel = new JPanel();
        setResizable(false);
        gbc = new GridBagConstraints();
        gbc.insets= new Insets(30,1,1,1);
        gbc.anchor=GridBagConstraints.PAGE_START;
        panel.setLayout(new GridBagLayout());
        ImageIcon image = new ImageIcon("Project Folder/data/Images/fxiconkitchen.png");
        Image img = image.getImage();
        setIconImage(img);
        JLabel kitchenIcon = new JLabel(scaler.scaleImg(image,150));
        gbc.gridy=1;
        gbc.gridx=0;
        panel.add(kitchenIcon,gbc);
        gbc.gridy=2;

        JLabel Lancasters = new JLabel("Lancaster's Kitchen Login Page");
        panel.add(Lancasters,gbc);
        JLabel username = new JLabel("Username");
        gbc.insets= new Insets(15,1,1,1);
        gbc.gridy=3;
        panel.add(username,gbc);
        userField = new JTextField(15);
        gbc.gridy=4;
        panel.add(userField,gbc);
        JLabel password = new JLabel("Password");
        gbc.gridy=5;
        panel.add(password,gbc);

        passField = new JPasswordField(15);

        gbc.gridy=6;
        panel.add(passField,gbc);


        ImageIcon loginB = new ImageIcon("Project Folder/src/main/resources/button_login.png");
        ImageIcon loginHover = new ImageIcon("Project Folder/src/main/resources/button_login_hover.png");
        loginButton = new JButton(loginB);
        loginButton.setPreferredSize(new Dimension(loginB.getIconWidth(),loginB.getIconHeight()));
        loginButton.setBorder(null);
        loginButton.setContentAreaFilled(false);
        loginButton.setRolloverIcon(loginHover);
        gbc.gridy=7;
        panel.add(loginButton,gbc);
        loginButton.addActionListener(actionListener);

        incorrectDetails();

        add(panel);
    }



    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                checkCredentials();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    };

    private void checkCredentials() throws SQLException {
            databaseDataCon database = new databaseDataCon();
            Connection con = database.returnConnection();
            String username = userField.getText();
            String password = passField.getText();
            String userActual=null;
            String passActual=null;
            PreparedStatement authState = con.prepareStatement("SELECT * FROM Authentication WHERE Username=? AND Password=?");
            authState.setString(1,username);
            authState.setString(2,password);
            ResultSet results = authState.executeQuery();
            while(results.next()){
                userActual = results.getString(1);
                passActual = results.getString(2);
                System.out.println(userActual+" "+username+" "+passActual+" "+password);
            }
        if (Objects.equals(username, userActual) && Objects.equals(password, passActual)){
            System.out.println("Correct Credentials");
            incorrect.setForeground(Color.GREEN);
            incorrect.setText("Logging in");
            dispose();
            MainMenu menu = new MainMenu(username);
        }
        else if(!Objects.equals(username, userActual) && !Objects.equals(password, passActual)){
            incorrect.setText("Username or Password is incorrect");
            repaint();
            System.out.println("Incorrect");
        }


    }

    private void incorrectDetails(){
        incorrect = new JLabel("                                          ");
        incorrect.setForeground(Color.red);
        gbc.gridx=0; // Center horizontally
        gbc.gridy=8; // Place it at the bottom
        gbc.gridwidth = 2; // Span over two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center horizontally

        panel.add(incorrect,gbc);
    }

}
