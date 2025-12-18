/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.app.warehouse.ui.warhouse;
import com.app.warehouse.dao.WarehouseDao;
 import com.app.warehouse.dao.impl.WarehouseDaoImpl;
 import com.app.warehouse.model.Warehouse;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author MUHAMMAD FADHILLAH
 */
public class WarehouseFormDialog extends JDialog {
    private JLabel lblTitle;
    private JLabel lblName;
    private JLabel lblLocation;
    private JPanel formPanel;
    private JPanel buttonPanel;
    private int warehouseId = 0;
    private WarehouseDao dao = new WarehouseDaoImpl();
    

    // Constructor 1
    public WarehouseFormDialog(Frame parent, boolean modal) {
        super(parent, true);
    initUI();        // wajib
    setTitle("Add Warehouse");
    }
    

    // Constructor 2
public WarehouseFormDialog(Frame parent, int id) { // EDIT
    super(parent, true);
    this.warehouseId = id;
    initUI();
    loadData();
}
    private void initUI() {
        setTitle("Warehouse Form");
        setSize(450, 400); // Sedikit lebih tinggi karena ada Capacity
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        initComponentsManual();
        initDesign();
        setupLayout();
    }

    private void initComponentsManual() {
        lblTitle = new JLabel("Form Data Warehouse");
        lblName = new JLabel("Warehouse Name");
        lblLocation = new JLabel("Location");
        
        txtName = new JTextField(20);
        txtLocation = new JTextField(20);
        
        btnSave = new JButton("Save Data");
        btnCancel = new JButton("Cancel");

        formPanel = new JPanel(new GridBagLayout());
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); 

        // Logic Tombol
        btnSave.addActionListener(this::btnSaveActionPerformed);
        btnCancel.addActionListener(e -> dispose());
    }

    private void setupLayout() {
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Baris 1: Name
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0; formPanel.add(lblName, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1; formPanel.add(txtName, gbc);

        // Baris 2: Location
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0; formPanel.add(lblLocation, gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1; formPanel.add(txtLocation, gbc);

        // Button Panel
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(229, 231, 235))); 
        buttonPanel.add(btnCancel); 
        buttonPanel.add(btnSave);   

        // Title Panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 0, 0));
        titlePanel.add(lblTitle);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initDesign() {
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(30, 58, 138));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        lblName.setFont(labelFont);
        lblLocation.setFont(labelFont);

        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
        txtName.setFont(inputFont);
        txtLocation.setFont(inputFont);
        
        Dimension fieldSize = new Dimension(200, 35);
        txtName.setPreferredSize(fieldSize);
        txtLocation.setPreferredSize(fieldSize);

        styleButton(btnSave, new Color(59, 130, 246)); 
        styleButton(btnCancel, new Color(156, 163, 175)); 
    }

    private void styleButton(JButton button, Color bg) {
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(110, 40)); 
    }
    
    private static final Logger logger =
        Logger.getLogger(WarehouseFormDialog.class.getName());

        private void loadData() {
        try {
            WarehouseDao dao = new WarehouseDaoImpl();
            Warehouse w = dao.findById(warehouseId);

            txtName.setText(w.getName());
            txtLocation.setText(w.getLocation());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal load data");
            e.printStackTrace();
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

        txtName = new javax.swing.JTextField();
        txtLocation = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtName.setText("Name");
        txtName.addActionListener(this::txtNameActionPerformed);

        txtLocation.setText("Location");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(this::btnCancelActionPerformed);

        btnSave.setText("Save");
        btnSave.addActionListener(this::btnSaveActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(btnSave)
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnSave))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
    String name = txtName.getText().trim();
    String location = txtLocation.getText().trim();

    if (name.isEmpty() || location.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "Semua kolom harus diisi!",
                "Validasi",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(name);
        warehouse.setLocation(location);

        WarehouseDao dao = new WarehouseDaoImpl();

        if (warehouseId == 0) {
            // ADD
            dao.create(warehouse);
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan");
        } else {
            // EDIT
            warehouse.setId(warehouseId);
            dao.update(warehouse);
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
        }

        dispose();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Gagal menyimpan data: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(Level.SEVERE, null, ex);

        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                WarehouseFormDialog dialog = new WarehouseFormDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
