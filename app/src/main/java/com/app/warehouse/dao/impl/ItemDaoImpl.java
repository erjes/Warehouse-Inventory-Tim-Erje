package com.app.warehouse.dao.impl;

import com.app.warehouse.dao.ItemDao;
import com.app.warehouse.model.Item;
import com.app.warehouse.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    @Override
    public int create(Item item) throws SQLException {
        String sql = """
            INSERT INTO item (warehouse_id, name, category, price, quantity)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, item.getWarehouseId());
            ps.setString(2, item.getSku());
            ps.setString(3, item.getName());
            ps.setString(4, item.getCategory());
            ps.setBigDecimal(5, item.getPrice());
            ps.setInt(6, item.getQuantity());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    @Override
    public Item findById(int id) throws SQLException {
        String sql = "SELECT * FROM item WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? map(rs) : null;
        }
    }

    @Override
    public List<Item> findAll() throws SQLException {
        List<Item> list = new ArrayList<>();
        String sql = "SELECT * FROM item";

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
    public List<Item> findByWarehouse(int warehouseId) throws SQLException {
        List<Item> list = new ArrayList<>();
        String sql = "SELECT * FROM item WHERE warehouse_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, warehouseId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    @Override
    public int update(Item item) throws SQLException {
        String sql = """
            UPDATE item SET warehouse_id=?, sku=?, name=?, category=?, price=?, quantity=?
            WHERE id=?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, item.getWarehouseId());
            ps.setString(2, item.getSku());
            ps.setString(3, item.getName());
            ps.setString(4, item.getCategory());
            ps.setBigDecimal(5, item.getPrice());
            ps.setInt(6, item.getQuantity());
            ps.setInt(7, item.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int updateQuantity(int itemId, int newQuantity, Connection conn) throws SQLException {
        String sql = "UPDATE item SET quantity=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newQuantity);
            ps.setInt(2, itemId);
            return ps.executeUpdate();
        }
    }

    @Override
    public int updateQuantityDelta(int itemId, int delta, Connection conn) throws SQLException {
        String sql = "UPDATE item SET quantity = quantity + ? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, delta);
            ps.setInt(2, itemId);
            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "DELETE FROM item WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    private Item map(ResultSet rs) throws SQLException {
        Item i = new Item();
        i.setId(rs.getInt("id"));
        i.setWarehouseId(rs.getInt("warehouse_id"));
        i.setName(rs.getString("name"));
        i.setCategory(rs.getString("category"));
        i.setPrice(rs.getBigDecimal("price"));
        i.setQuantity(rs.getInt("quantity"));
        i.setCreatedAt(rs.getTimestamp("created_at"));
        i.setUpdatedAt(rs.getTimestamp("updated_at"));
        return i;
    }
}
