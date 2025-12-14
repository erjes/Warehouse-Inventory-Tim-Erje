package com.app.warehouse.dao.impl;

import com.app.warehouse.dao.TransactionDao;
import com.app.warehouse.model.Transaction;
import com.app.warehouse.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    @Override
    public int create(Transaction t, Connection conn) throws SQLException {
        String sql = """
            INSERT INTO transaction
            (user_id, item_id, transaction_type, supplier_id, customer_id, quantity, note)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, t.getUserId());
            ps.setInt(2, t.getItemId());
            ps.setString(3, t.getTransactionType());
            ps.setObject(4, t.getSupplierId());
            ps.setObject(5, t.getCustomerId());
            ps.setInt(6, t.getQuantity());
            ps.setString(7, t.getNote());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    @Override
    public Transaction findById(int id) throws SQLException {
        String sql = "SELECT * FROM transaction WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? map(rs) : null;
        }
    }

    @Override
    public List<Transaction> findAll() throws SQLException {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transaction";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    @Override
    public List<Transaction> findByDateRange(Date start, Date end) throws SQLException {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transaction WHERE transaction_date BETWEEN ? AND ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, new Timestamp(start.getTime()));
            ps.setTimestamp(2, new Timestamp(end.getTime()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    @Override
    public List<Transaction> findByUser(int userId) throws SQLException {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transaction WHERE user_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    @Override
    public List<Transaction> findByItem(int itemId) throws SQLException {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transaction WHERE item_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, itemId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    private Transaction map(ResultSet rs) throws SQLException {
        Transaction t = new Transaction();
        t.setId(rs.getInt("id"));
        t.setUserId(rs.getInt("user_id"));
        t.setItemId(rs.getInt("item_id"));
        t.setTransactionType(rs.getString("transaction_type"));
        t.setSupplierId((Integer) rs.getObject("supplier_id"));
        t.setCustomerId((Integer) rs.getObject("customer_id"));
        t.setQuantity(rs.getInt("quantity"));
        t.setTransactionDate(rs.getTimestamp("transaction_date"));
        t.setNote(rs.getString("note"));
        return t;
    }
}
