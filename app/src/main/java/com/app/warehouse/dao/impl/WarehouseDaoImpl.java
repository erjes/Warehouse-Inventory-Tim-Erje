package com.app.warehouse.dao.impl;

import com.app.warehouse.dao.WarehouseDao;
import com.app.warehouse.model.Warehouse;
import com.app.warehouse.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDaoImpl implements WarehouseDao {

    @Override
    public Warehouse findById(int id) throws SQLException {
        String sql = "SELECT * FROM warehouse WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? map(rs) : null;
        }
    }

    @Override
    public List<Warehouse> findAll() throws SQLException {
        List<Warehouse> list = new ArrayList<>();
        String sql = "SELECT * FROM warehouse";

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
    public int create(Warehouse warehouse) throws SQLException {
        String sql = "INSERT INTO warehouse (name, location) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, warehouse.getName());
            ps.setString(2, warehouse.getLocation());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    @Override
    public int update(Warehouse warehouse) throws SQLException {
        String sql = "UPDATE warehouse SET name=?, location=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, warehouse.getName());
            ps.setString(2, warehouse.getLocation());
            ps.setInt(3, warehouse.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "DELETE FROM warehouse WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    private Warehouse map(ResultSet rs) throws SQLException {
        Warehouse w = new Warehouse();
        w.setId(rs.getInt("id"));
        w.setName(rs.getString("name"));
        w.setLocation(rs.getString("location"));
        return w;
    }
}
