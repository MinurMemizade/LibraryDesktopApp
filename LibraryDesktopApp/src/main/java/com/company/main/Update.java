package com.company.main;

import dao.impl.DAOBooks;
import entity.Book;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Update {
    JFrame frame=new JFrame();
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtPrice;
    private JButton saveButton;
    private JPanel panel1;

    public Update() throws Exception {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setContentPane(panel1);
        Books books=new Books();
        int id= books.getId();
        Book book= DAOBooks.getById(id);
        txtTitle.setText(book.getTitle());
        txtAuthor.setText(book.getAuthors());
        txtPrice.setText(String.valueOf(book.getPrice()));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DAOBooks.updateBooks(id,txtTitle.getText(),txtAuthor.getText(), Double.parseDouble(txtPrice.getText()));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                frame.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Books::new);
    }
}
