/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.app.warehouse.ui.warhouse;

import com.app.warehouse.dao.WarehouseDao;
import com.app.warehouse.dao.impl.WarehouseDaoImpl;
import com.app.warehouse.model.Warehouse;
import java.util.List;
import com.formdev.flatlaf.FlatLightLaf;
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
import java.sql.SQLException;
/**
 *
 * @author MUHAMMAD FADHILLAH
 */
public class WarehousePanel extends JPanel {

    // --- 1. Deklarasi Variabel ---
    private JLabel lblTitle;
    private JScrollPane scrollPane;
    private JPanel headerPanel;
    private JPanel buttonPanel;

    public WarehousePanel() {
        // 1. Inisialisasi Komponen Manual
        initComponentsManual();
        
        // 2. Setup Tabel
        initTable();
        
        // 3. Styling (Warna & Font)
        initDesign();
        
        // 4. Tata Letak (Layout)
        setupLayout();
            loadData();
    }

    private void initComponentsManual() {
        // Inisialisasi variabel
        btnAdd = new JButton("Add");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        lblTitle = new JLabel("Manage Warehouse"); // Judul diperbaiki
        tblWarehouse = new JTable();
        scrollPane = new JScrollPane(tblWarehouse);
        headerPanel = new JPanel();
        buttonPanel = new JPanel();

        // Action Listeners (Logika Tombol)
btnAdd.addActionListener(this::btnAddActionPerformed);
btnEdit.addActionListener(this::btnEditActionPerformed);
btnDelete.addActionListener(this::btnDeleteActionPerformed);

    }

    private void setupLayout() {
        // Gunakan BorderLayout agar responsif full screen
        this.setLayout(new BorderLayout(20, 20)); 
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        // --- Header Section ---
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setOpaque(false);

        // Judul di Kiri
        headerPanel.add(lblTitle, BorderLayout.WEST);

        // Tombol di Kanan
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        
        headerPanel.add(buttonPanel, BorderLayout.EAST);

        // Pasang ke Panel Utama
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }
    
private void loadData() {
    DefaultTableModel model = (DefaultTableModel) tblWarehouse.getModel();
    model.setRowCount(0);

    WarehouseDao dao = new WarehouseDaoImpl();

    try {
        List<Warehouse> list = dao.findAll();

        for (Warehouse w : list) {
            model.addRow(new Object[]{
                w.getId(),
                w.getName(),
                w.getLocation()
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
            this,
            "Gagal memuat data warehouse!\n" + e.getMessage(),
            "Error Database",
            JOptionPane.ERROR_MESSAGE
        );
        e.printStackTrace();
    }
}



    private void initDesign() {
        this.setBackground(new Color(243, 244, 246)); // Background abu muda

        // Styling Judul
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(30, 58, 138));

        // Styling Tombol (Biru, Kuning, Merah)
        styleButton(btnAdd, new Color(59, 130, 246));   
        styleButton(btnEdit, new Color(234, 179, 8));   
        styleButton(btnDelete, new Color(239, 68, 68)); 

        // Styling Tabel
        tblWarehouse.setRowHeight(35);
        tblWarehouse.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblWarehouse.setGridColor(new Color(229, 231, 235));
        tblWarehouse.setShowVerticalLines(false);
        
        // Header Tabel
        tblWarehouse.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblWarehouse.getTableHeader().setBackground(new Color(30, 58, 138));
        tblWarehouse.getTableHeader().setForeground(Color.WHITE);
        tblWarehouse.getTableHeader().setPreferredSize(new Dimension(0, 40));
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
        // Model tabel default (Nanti diganti dengan data dari Database)
        tblWarehouse.setModel(new DefaultTableModel(
            new Object[][]{}, 
            new String[]{"ID", "Name", "Location"} // Sesuaikan kolom Warehouse
        ) {
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        });
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
        tblWarehouse = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        tblWarehouse.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblWarehouse);

        btnAdd.setText("Add");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnEdit.setText("Edit");
        btnEdit.addActionListener(this::btnEditActionPerformed);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        jLabel1.setText("Manage Warhouse");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEdit)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEdit)
                        .addComponent(btnDelete)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
    int row = tblWarehouse.getSelectedRow();

    if (row == -1) {
        JOptionPane.showMessageDialog(this,
                "Pilih data warehouse yang ingin diedit!",
                "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int id = (int) tblWarehouse.getValueAt(row, 0); // kolom ID

    JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);

    WarehouseFormDialog dialog =
            new WarehouseFormDialog(parent, id); 

    dialog.setLocationRelativeTo(parent);
    dialog.setVisible(true);

    loadData(); // refresh tabel
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
    JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);

    WarehouseFormDialog dialog = new WarehouseFormDialog(parent, true); 

    dialog.setLocationRelativeTo(parent);
    dialog.setVisible(true);

    loadData(); // refresh tabel
      // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
int row = tblWarehouse.getSelectedRow();
        if (row == -1) {
             JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!", "Warning", JOptionPane.WARNING_MESSAGE);
             return;
        }
        
        // Konfirmasi Hapus
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Yakin ingin menghapus data ini?", 
            "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int id = (int) tblWarehouse.getValueAt(row, 0); // Ambil ID
                
                // Panggil DAO untuk hapus (Fixing the logic here)
                WarehouseDao dao = new WarehouseDaoImpl();
                dao.delete(id); 
                
                loadData(); // Refresh tabel setelah hapus
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

public static void main(String[] args) {
        
        // 1. PASANG FLATLAF DULU (Agar saat test, tampilan tetap bagus)
        try {
            // Pilih salah satu: FlatLightLaf atau FlatDarkLaf
            javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Gagal load FlatLaf");
        }

        // 2. Jalankan Panel Test
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Warehouse Panel Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1024, 768); 
            frame.setLocationRelativeTo(null); // Tengah layar
            
            // Masukkan panel yang mau ditest
            frame.setContentPane(new WarehousePanel());
            
            frame.setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblWarehouse;
    // End of variables declaration//GEN-END:variables
}
