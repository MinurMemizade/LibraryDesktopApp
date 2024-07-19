package com.company.main;

import dao.impl.DAOUser;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {
    JFrame register=new JFrame();
    private JTextField txtName;
    private JTextField txtUsername;
    private JTextField txtSurname;
    private JPasswordField txtPassword;
    private JButton registerButton;
    private JPanel registerPanel;
    private JButton backButton;
    private JLabel message;

    public Register()
    {
        register.setContentPane(registerPanel);
        register.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        register.setLocationRelativeTo(null);
        register.setExtendedState(JFrame.MAXIMIZED_BOTH);
        register.pack();
        register.setVisible(true);
        txtUsername.setVisible(true);
        txtPassword.setVisible(true);
        registerButton.setVisible(true);
        registerPanel.setVisible(true);
        registerButton.setVisible(true);
        message.setVisible(false);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user=new User();
                user.setId(1);
                user.setName(txtName.getText());
                user.setSurname(txtSurname.getText());
                user.setUsername(txtUsername.getText());
                user.setPassword(txtPassword.getText());
                try {
                    DAOUser.addUser(user);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                message.setVisible(true);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Login();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
