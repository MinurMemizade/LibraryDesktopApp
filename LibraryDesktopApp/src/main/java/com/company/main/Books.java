package com.company.main;

import com.mysql.cj.log.Log;
import dao.impl.DAOBooks;
import dao.impl.DAOUser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Books {
    JFrame books=new JFrame();
    private JButton getAllBooksButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTable tblBooks;
    private JTextField txtDelete;
    private JTextField txtUpdate;
    private JPanel panel1;
    private JButton addButton;
    private static int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Books()
    {
        books.setContentPane(panel1);
        books.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        books.setLocationRelativeTo(null);
        books.setExtendedState(JFrame.MAXIMIZED_BOTH);
        books.pack();
        books.setVisible(true);
        getAllBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DAOUser.getConnection();
                    String sql = "SELECT * FROM books";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();
                    ResultSetMetaData rsmd = rs.getMetaData();
                    DefaultTableModel dtm = (DefaultTableModel) tblBooks.getModel();
                    dtm.setRowCount(0);
                    int columns = rsmd.getColumnCount();
                    String[] colName = new String[columns];
                    for (int i = 0; i < columns; i++) {
                        colName[i] = rsmd.getColumnName(i + 1);
                    }
                    dtm.setColumnIdentifiers(colName);
                    while (rs.next()) {
                        String id = rs.getString(1);
                        String title = rs.getString(2);
                        String author = rs.getString(3);
                        String price = rs.getString(4);
                        String[] row = {id, title, author, price};
                        dtm.addRow(row);
                    }
                    rs.close();
                    stmt.close();
                    connection.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setId(Integer.parseInt(txtUpdate.getText()));
                    books.setVisible(false);
                    new Update().frame.setVisible(true);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMenu().addMenu.setVisible(true);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DAOBooks.delete(Integer.parseInt(txtDelete.getText()));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
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
        SwingUtilities.invokeLater(() -> {
            try {
                new Update();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
