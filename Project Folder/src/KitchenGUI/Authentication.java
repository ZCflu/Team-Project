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
    public Authentication(){
        setAttributes();
        addPanel();
        show();

    }

    private void setAttributes(){
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void addPanel(){
        panel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        JLabel Lancasters = new JLabel("Lancaster's Kitchen Login Page");
        panel.add(Lancasters);
        JLabel username = new JLabel("Username");
        gbc.insets= new Insets(15,1,1,1);
        gbc.gridy=1;
        panel.add(username,gbc);
        userField = new JTextField(15);
        gbc.gridy=2;
        panel.add(userField,gbc);
        JLabel password = new JLabel("Password");
        gbc.gridy=3;
        panel.add(password,gbc);

        passField = new JTextField(15);
        gbc.gridy=4;
        panel.add(passField,gbc);

        loginButton = new JButton("Login");
        gbc.gridy=5;
        panel.add(loginButton,gbc);

        loginButton.addActionListener(actionListener);

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
            dispose();
            MainMenu menu = new MainMenu();
        }
        else if(!Objects.equals(username, userActual) && !Objects.equals(password, passActual)){
            System.out.println("Incorrect");
        }


    }
}
