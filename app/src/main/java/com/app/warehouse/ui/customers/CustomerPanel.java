/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.app.warehouse.ui.customers; // Sesuaikan dengan package kamu

import com.app.warehouse.ui.item.ItemPanel;
import com.app.warehouse.util.DBConnection; 
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author MUHAMMAD FADHILLAH
 */
public class CustomerPanel extends javax.swing.JPanel {
    private JTextField txtSearch;
    private DefaultTableModel model;
    private TableRowSorter<DefaultTableModel> sorter;
    /**
     * Creates new form CustomerPanel
     */
    public CustomerPanel() {
        initComponents();
        setupCustomLayout();
        loadData();
    }
    private void setupCustomLayout() {
        this.removeAll(); // Hapus layout bawaan NetBeans
        
        this.setLayout(new BorderLayout(15, 15));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        // A. BAGIAN HEADER (Judul & Search)
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel lblTitle = new JLabel("Manage Customers");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtSearch = new JTextField(20);
        txtSearch.putClientProperty("JTextField.placeholderText", "Search customers...");
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterData(txtSearch.getText());
            }
        });
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(txtSearch);

        headerPanel.add(lblTitle, BorderLayout.WEST);
        headerPanel.add(searchPanel, BorderLayout.EAST);

        // B. BAGIAN SIDEBAR (Tombol)
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(150, 0));

        styleButton(btnAdd);
        styleButton(btnEdit);
        styleButton(btnDelete);

        sidePanel.add(btnAdd);
        sidePanel.add(Box.createVerticalStrut(10));
        sidePanel.add(btnEdit);
        sidePanel.add(Box.createVerticalStrut(10));
        sidePanel.add(btnDelete);
        sidePanel.add(Box.createVerticalGlue());

        // C. KONFIGURASI TABEL
        // Kolom sesuai ERD: ID, Nama, Alamat
        String[] columns = {"ID", "Customer Name", "Address"};

        model = new DefaultTableModel(null, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Agar tidak bisa diedit langsung di tabel
            }
        };
        
        // tblCustomer adalah nama variabel tabel dari NetBeans
        tblCustomer.setModel(model);
        tblCustomer.setRowHeight(30);
        tblCustomer.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        // Pasang Sorter untuk fitur search
        sorter = new TableRowSorter<>(model);
        tblCustomer.setRowSorter(sorter);

        // D. SUSUN SEMUA KE LAYOUT UTAMA
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(sidePanel, BorderLayout.WEST);
        this.add(jScrollPane1, BorderLayout.CENTER); // jScrollPane1 wadah tabel bawaan NetBeans
        
        this.revalidate();
        this.repaint();
    }

    private void styleButton(JButton btn) {
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
    }

    // --- LOGIC DATABASE (Load Data) ---
    private void loadData() {
        model.setRowCount(0);
        
        // Query disesuaikan dengan ERD (tabel customers)
        String sql = "SELECT customer_id, customer_name, address FROM customers";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("customer_id"));
                row.add(rs.getString("customer_name"));
                row.add(rs.getString("address"));
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    private void filterData(String keyword) {
        if (keyword.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblCustomer);

        btnAdd.setText("Add");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnEdit.setText("Edit");
        btnEdit.addActionListener(this::btnEditActionPerformed);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        jLabel1.setText("Manage Costomers");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnAdd)
                .addGap(18, 18, 18)
                .addComponent(btnEdit)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(142, 142, 142))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
int selectedRow = tblCustomer.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a customer to edit!");
            return;
        }

        int modelRow = tblCustomer.convertRowIndexToModel(selectedRow);
        int id = (int) model.getValueAt(modelRow, 0); // Ambil ID dari kolom 0

        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        // Membuka Dialog Edit dengan ID
        new CustomerFormDialog(parent, id).setVisible(true);
        loadData();        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        // Membuka CustomerFormDialog (Pastikan file ini sudah ada logic-nya)
        new CustomerFormDialog(parent, true).setVisible(true);
        loadData(); // Refresh tabel setelah dialog tutup        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
int selectedRow = tblCustomer.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a customer to delete!");
            return;
        }

        int modelRow = tblCustomer.convertRowIndexToModel(selectedRow);
        int id = (int) model.getValueAt(modelRow, 0);
        String name = (String) model.getValueAt(modelRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete '" + name + "'?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement("DELETE FROM customers WHERE customer_id = ?")) {
                
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                loadData(); // Refresh data
                JOptionPane.showMessageDialog(this, "Customer deleted successfully.");
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error deleting: " + e.getMessage());
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed
public static void main(String[] args) {
    try {
        UIManager.setLookAndFeel(new FlatLightLaf());
    } catch (Exception e) {
        e.printStackTrace();
    }

    SwingUtilities.invokeLater(() -> {
        JFrame frame = new JFrame("Customer Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        // PENTING: pakai CustomerPanel, bukan ItemPanel
        frame.setContentPane(new CustomerPanel());

        frame.setVisible(true);
    });
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCustomer;
    // End of variables declaration//GEN-END:variables
}
