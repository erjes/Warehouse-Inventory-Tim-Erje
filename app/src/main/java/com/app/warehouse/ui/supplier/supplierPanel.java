/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.app.warehouse.ui.supplier;

import com.app.warehouse.dao.SupplierDao;
import com.app.warehouse.dao.impl.SupplierDaoImpl;
import com.app.warehouse.model.Supplier;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MUHAMMAD FADHILLAH
 */
public class SupplierPanel extends javax.swing.JPanel {

    // --- 1. Deklarasi Variabel ---
private JLabel lblTitle;
private JTable tableSupplier;
private JScrollPane scrollPane;
private JPanel headerPanel;
private JPanel buttonPanel;
private Supplier supplier;


    public SupplierPanel() {
        initComponentsManual();
        initTable();
        initDesign();
        setupLayout();
        loadData();
    }

    private void initComponentsManual() {
        // Inisialisasi variabel
        btnAdd = new JButton("Add");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        lblTitle = new JLabel("Manage Supplier");
        tableSupplier = new JTable();
        scrollPane = new JScrollPane(tableSupplier);
        headerPanel = new JPanel();
        buttonPanel = new JPanel();

        // Action Listeners (Sambungkan tombol ke fungsi di bawah)
        btnAdd.addActionListener(this::btnAddActionPerformed);
        btnEdit.addActionListener(this::btnEditActionPerformed);
        btnDelete.addActionListener(this::btnDeleteActionPerformed);
    }

    private void setupLayout() {
        this.setLayout(new BorderLayout(20, 20)); 
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        // --- Header Section ---
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setOpaque(false);

        headerPanel.add(lblTitle, BorderLayout.WEST);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        
        headerPanel.add(buttonPanel, BorderLayout.EAST);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void initDesign() {
        this.setBackground(new Color(243, 244, 246)); 

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(30, 58, 138));

        styleButton(btnAdd, new Color(59, 130, 246));   
        styleButton(btnEdit, new Color(234, 179, 8));   
        styleButton(btnDelete, new Color(239, 68, 68)); 

        tableSupplier.setRowHeight(35);
        tableSupplier.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tableSupplier.setGridColor(new Color(229, 231, 235));
        tableSupplier.setShowVerticalLines(false);
        
        tableSupplier.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableSupplier.getTableHeader().setBackground(new Color(30, 58, 138));
        tableSupplier.getTableHeader().setForeground(Color.WHITE);
        tableSupplier.getTableHeader().setPreferredSize(new Dimension(0, 40));
    }

    private void styleButton(JButton button, Color bg) {
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(100, 35));
    }

    private void initTable() {
        tableSupplier.setModel(new DefaultTableModel(
            new Object[][]{}, 
            new String[]{"ID", "Name", "Contact"} 
        ) {
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        });
    }
private void loadData() {
    try {
        SupplierDao dao = new SupplierDaoImpl();
        java.util.List<Supplier> list = dao.findAll();

        DefaultTableModel model =
            (DefaultTableModel) tableSupplier.getModel();
        model.setRowCount(0);

        for (Supplier s : list) {
            model.addRow(new Object[]{
                s.getId(),
                s.getName(),
                s.getContact()
            });
        }

    } catch (java.sql.SQLException e) {
        JOptionPane.showMessageDialog(this,
            "Gagal load data supplier:\n" + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
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

        panelTop = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        scrollSupplier = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        btnAdd.setText("Add");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnEdit.setText("Edit");
        btnEdit.addActionListener(this::btnEditActionPerformed);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollSupplier.setViewportView(jTable1);

        jLabel1.setText("Manage Supplier");

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete)
                    .addComponent(btnEdit)
                    .addComponent(btnAdd))
                .addGap(26, 26, 26)
                .addComponent(scrollSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(169, 169, 169))
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTopLayout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        SupplierFormDialog dialog = new SupplierFormDialog(parent, true);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
        loadData(); 
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
 int row = tableSupplier.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this,
                "Pilih data supplier terlebih dahulu!",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    DefaultTableModel model = (DefaultTableModel) tableSupplier.getModel();

    Supplier s = new Supplier();
    s.setId((Integer) model.getValueAt(row, 0));
    s.setName((String) model.getValueAt(row, 1));
    s.setContact((String) model.getValueAt(row, 2));

    JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
    SupplierFormDialog dialog = new SupplierFormDialog(parent, true);
dialog.setSupplier(s); // kirim data ke dialog
    dialog.setLocationRelativeTo(parent);
    dialog.setVisible(true);

    loadData();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
    int row = tableSupplier.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this,
                "Pilih data supplier yang ingin dihapus!",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this,
            "Yakin ingin menghapus supplier ini?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION);

    if (confirm != JOptionPane.YES_OPTION) return;

    try {
        int id = (Integer) tableSupplier.getValueAt(row, 0);
        SupplierDao dao = new SupplierDaoImpl();
        dao.delete(id);

        JOptionPane.showMessageDialog(this, "Data supplier berhasil dihapus");
        loadData();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Gagal menghapus data: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnDeleteActionPerformed
public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Supplier Panel Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1024, 768); 
            frame.setLocationRelativeTo(null); 
            frame.setContentPane(new SupplierPanel());
            frame.setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panelTop;
    private javax.swing.JScrollPane scrollSupplier;
    // End of variables declaration//GEN-END:variables
}