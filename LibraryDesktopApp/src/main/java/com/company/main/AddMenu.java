package com.company.main;

import dao.impl.DAOBooks;
import entity.Book;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMenu {
    JFrame addMenu=new JFrame();
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtPrice;
    private JButton saveButton;
    private JPanel addPanel;

    public AddMenu() {
        addMenu.setContentPane(addPanel);
        addMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addMenu.setLocationRelativeTo(null);
        addMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        addMenu.pack();
        addMenu.setVisible(true);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book book = new Book();
                book.setId(1);
                book.setTitle(txtTitle.getText());
                book.setAuthors(txtAuthor.getText());
                book.setPrice(Double.parseDouble(txtPrice.getText()));
                try {
                    DAOBooks.addBook(book);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                addMenu.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Books::new);
    }
}
