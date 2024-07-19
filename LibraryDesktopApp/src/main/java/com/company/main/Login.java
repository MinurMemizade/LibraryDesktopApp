package com.company.main;

import com.mysql.cj.log.Log;
import dao.impl.DAOUser;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Login {
    JFrame login=new JFrame();
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JButton logInButton;
    private JButton registerButton;
    private JPanel loginPanel;

    public Login() throws Exception {
        login.setContentPane(loginPanel);
        login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        login.setLocationRelativeTo(null);
        login.setExtendedState(JFrame.MAXIMIZED_BOTH);
        login.pack();
        login.setVisible(true);
        txtUsername.setVisible(true);
        txtPassword.setVisible(true);
        registerButton.setVisible(true);
        loginPanel.setVisible(true);
        logInButton.setVisible(true);
        List<User>userList=DAOUser.getAllUsers();
        logInButton.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            boolean loggedIn = false;
            for (User user : userList) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    loggedIn = true;
                    break;
                }
            }
            if (loggedIn) {
                new Books().books.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(login, "Incorrect username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register().register.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Login();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
