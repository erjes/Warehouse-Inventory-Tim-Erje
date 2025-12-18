/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.app.warehouse.ui.item;

import com.app.warehouse.dao.ItemDao;
import com.app.warehouse.dao.impl.ItemDaoImpl;
import com.app.warehouse.model.Item;
import com.formdev.flatlaf.FlatLightLaf;
import com.app.warehouse.util.DBConnection;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ItemPanel extends javax.swing.JPanel {

    private JTextField txtSearch;
    private DefaultTableModel model;
    private TableRowSorter<DefaultTableModel> sorter;

    public ItemPanel() {
        // 1. Biarkan NetBeans menyiapkan komponen (tombol, tabel, dll)
        initComponents();
        
        // 2. Langsung kita atur ulang tampilannya sesuai kemauan kita
        setupCustomLayout(); 
        
        // 3. Load data
        loadData();
    }

    // --- LOGIKA MANUAL KITA (Letakkan di bawah Constructor) ---
    
    private void setupCustomLayout() {
        // Hapus semua layout bawaan NetBeans (panelTop, dll) dari tampilan utama
        this.removeAll();
        
        // Pasang Layout Baru
        this.setLayout(new BorderLayout(15, 15));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        // A. BAGIAN HEADER
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel lblTitle = new JLabel("Manage Item");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtSearch = new JTextField(20);
        txtSearch.putClientProperty("JTextField.placeholderText", "Search item...");
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
        // Kita "curi" tombol yang sudah dibuat oleh NetBeans (btnAdd, dll)
        // dan masukkan ke panel baru kita.
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

        // C. BAGIAN TABEL
        // Kita konfigurasi ulang tabel yang sudah dibuat NetBeans (jTable1)
        String[] columns = {
            "ID", "Wh ID", "Item Name", "Category", 
            "Qty", "Price", "Created At", "Updated At"
        };

        // Buat model baru yang non-editable
        model = new DefaultTableModel(null, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(model);
        jTable1.setRowHeight(25);
        jTable1.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        // Setup Sorter
        sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);

        // D. MENYATUKAN SEMUANYA
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(sidePanel, BorderLayout.WEST);
        // tableModel adalah nama variabel JScrollPane bawaan NetBeans
        this.add(tableModel, BorderLayout.CENTER); 
        
        // Refresh tampilan agar perubahan layout terbaca
        this.revalidate();
        this.repaint();
    }

    private void styleButton(JButton btn) {
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
    }

    private void loadData() {
        model.setRowCount(0);
        String sql = "SELECT id, warehouse_id, name, category, quantity, price, created_at, updated_at FROM item";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("id"));
                row.add(rs.getInt("warehouse_id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("category"));
                row.add(rs.getInt("quantity"));
                row.add(rs.getDouble("price"));
                row.add(rs.getTimestamp("created_at"));
                row.add(rs.getTimestamp("updated_at"));
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading: " + e.getMessage());
        }
    }

    private void filterData(String keyword) {
        if (keyword.trim().isEmpty()) sorter.setRowFilter(null);
        else sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTop = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        tableModel = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        btnAdd.setText("Add Item");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnEdit.setText("Edit Item");
        btnEdit.addActionListener(this::btnEditActionPerformed);

        btnDelete.setText("Delete Item");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableModel.setViewportView(jTable1);
        jTable1.getAccessibleContext().setAccessibleName("");
        jTable1.getAccessibleContext().setAccessibleParent(jTable1);

        jLabel1.setText("Manage Item");

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(171, 171, 171))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                        .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDelete)
                            .addComponent(btnEdit)
                            .addComponent(btnAdd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tableModel, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTopLayout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete))
                    .addComponent(tableModel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(235, 235, 235))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
if (jTable1.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "Data masih kosong!");
        return;
    }

    int viewRow = jTable1.getSelectedRow();
    if (viewRow < 0) {
        JOptionPane.showMessageDialog(this, "Pilih item terlebih dahulu!");
        return;
    }

    // WAJIB konversi kalau pakai sorter / filter
    int modelRow = jTable1.convertRowIndexToModel(viewRow);

    int id = (int) model.getValueAt(modelRow, 0);

    JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
    new ItemFormDialog(parent, id).setVisible(true);

    loadData();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih item!");
            return;
        }

// kalau pakai sorter / filter
        int modelRow = jTable1.convertRowIndexToModel(row);

// ambil ID dari kolom pertama
        int id = (int) jTable1.getModel().getValueAt(modelRow, 0);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Hapus item?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                new ItemDaoImpl().delete(id);
                loadData();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        ItemFormDialog dialog = new ItemFormDialog(parent, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        loadData();
    }//GEN-LAST:event_btnAddActionPerformed

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Panel");
            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new ItemPanel());
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
    private javax.swing.JScrollPane tableModel;
    // End of variables declaration//GEN-END:variables
}
