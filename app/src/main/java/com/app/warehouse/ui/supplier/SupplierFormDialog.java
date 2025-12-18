/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
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

/**
 *
 * @author MUHAMMAD FADHILLAH
 */
public class SupplierFormDialog extends JDialog {

    // --- 1. Deklarasi Variabel ---
    private JButton btnCancel;
    private JLabel lblTitle;
    private JLabel lblName;
    private JLabel lblContact;
    private JPanel formPanel;
    private JPanel buttonPanel;
    private Supplier supplier;
    

    // Constructor 1 (Standar)
    public SupplierFormDialog(Frame parent, boolean modal) {
        super(parent, true);
    initUI();

    }


    private void initUI() {
        // Setup Dasar Dialog
        setTitle("Supplier Form");
        setSize(450, 350);
        setLocationRelativeTo(getParent()); // Muncul di tengah
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // 1. Inisialisasi Komponen
        initComponentsManual();

        // 2. Styling (Warna & Font)
        initDesign();

        // 3. Tata Letak (Layout)
        setupLayout();
    }

        private void fillForm() {
        if (supplier != null) {
            txtName.setText(supplier.getName());
            txtContact.setText(supplier.getContact());
        }
    }
        
    private void initComponentsManual() {
        lblTitle = new JLabel("Form Data Supplier");
        lblName = new JLabel("Supplier Name");
        lblContact = new JLabel("Contact Info");
        
        txtName = new JTextField(20);
        txtContact = new JTextField(20);
        
        // Perbaikan Nama Variabel agar tidak bingung
        btnSave = new JButton("Save Data");
        btnCancel = new JButton("Cancel");

        formPanel = new JPanel(new GridBagLayout());
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); 

        // Action Listeners
        btnSave.addActionListener(e -> actionSaveActionPerformed(null));
        btnCancel.addActionListener(e -> dispose()); // Tutup saat cancel
    }

    private void setupLayout() {
        // --- A. FORM SECTION (GridBagLayout) ---
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Baris 1: Nama
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        formPanel.add(lblName, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1;
        formPanel.add(txtName, gbc);

        // Baris 2: Kontak
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        formPanel.add(lblContact, gbc);

        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1;
        formPanel.add(txtContact, gbc);

        // --- B. BUTTON SECTION ---
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(229, 231, 235))); 
        buttonPanel.add(btnCancel); 
        buttonPanel.add(btnSave);   

        // --- C. JUDUL ---
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 0, 0));
        titlePanel.add(lblTitle);

        // Satukan semua ke layar
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initDesign() {
        // Styling Font & Warna
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(30, 58, 138));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        lblName.setFont(labelFont);
        lblContact.setFont(labelFont);

        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
        txtName.setFont(inputFont);
        txtContact.setFont(inputFont);
        
        txtName.setPreferredSize(new Dimension(200, 35));
        txtContact.setPreferredSize(new Dimension(200, 35));

        styleButton(btnSave, new Color(59, 130, 246)); // Biru
        styleButton(btnCancel, new Color(156, 163, 175)); // Abu-abu
    }

    private void styleButton(JButton button, Color bg) {
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(110, 40)); 
    }
    
    public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
    fillForm();
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtName = new javax.swing.JTextField();
        Name = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        Contact = new javax.swing.JLabel();
        actionSave = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtName.setText("Name Supplier");
        txtName.addActionListener(this::txtNameActionPerformed);

        Name.setText("Name");

        txtContact.setText("Contact");
        txtContact.addActionListener(this::txtContactActionPerformed);

        Contact.setText("Contact");

        actionSave.setText("Save");
        actionSave.addActionListener(this::actionSaveActionPerformed);

        btnSave.setText("Cancel");
        btnSave.addActionListener(this::btnSaveActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(actionSave))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Contact, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Name))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Contact))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actionSave)
                    .addComponent(btnSave))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
txtName.setText("");
    txtContact.setText("");
    
    // Perintah untuk menutup dialog
    dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private void actionSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionSaveActionPerformed

   String nameInput = txtName.getText().trim();
    String contactInput = txtContact.getText().trim();

    if (nameInput.isEmpty() || contactInput.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "Nama dan Kontak harus diisi!",
                "Validasi",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        Supplier supplier = new Supplier();
        supplier.setName(nameInput);
        supplier.setContact(contactInput);

        SupplierDao dao = new SupplierDaoImpl();
        dao.create(supplier);

        JOptionPane.showMessageDialog(this,
                "Data supplier berhasil disimpan");
        dispose();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Gagal menyimpan data: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_actionSaveActionPerformed

    /**
     * @param args the command line arguments
     */
public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            SupplierFormDialog dialog = new SupplierFormDialog(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Contact;
    private javax.swing.JLabel Name;
    private javax.swing.JButton actionSave;
    private javax.swing.JButton btnSave;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
