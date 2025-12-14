package com.app.warehouse.dao.impl;

import com.app.warehouse.dao.ReportDao;
import com.app.warehouse.model.Report;
import com.app.warehouse.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportDaoImpl implements ReportDao {

    @Override
    public Report findById(int id) throws SQLException {
        String sql = "SELECT * FROM report WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            return rs.next() ? map(rs) : null;
        }
    }

    @Override
    public List<Report> findAll() throws SQLException {
        List<Report> list = new ArrayList<>();
        String sql = "SELECT * FROM report";

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
    public List<Report> findByDateRange(Date start, Date end) throws SQLException {
        List<Report> list = new ArrayList<>();
        String sql = "SELECT * FROM report WHERE report_date BETWEEN ? AND ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(start.getTime()));
            ps.setDate(2, new java.sql.Date(end.getTime()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    @Override
    public int create(Report report) throws SQLException {
        String sql = """
            INSERT INTO report (user_id, report_date, report_type, description, file_path)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, report.getUserId());
            ps.setDate(2, new java.sql.Date(report.getReportDate().getTime()));
            ps.setString(3, report.getType());
            ps.setString(4, report.getDescription());
            ps.setString(5, report.getFilePath());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    @Override
    public int update(Report report) throws SQLException {
        String sql = """
            UPDATE report
            SET report_date = ?, report_type = ?, description = ?, file_path = ?
            WHERE id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(report.getReportDate().getTime()));
            ps.setString(2, report.getType());
            ps.setString(3, report.getDescription());
            ps.setString(4, report.getFilePath());
            ps.setInt(5, report.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "DELETE FROM report WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    //   ResultSet Mapper
    private Report map(ResultSet rs) throws SQLException {
        Report r = new Report();
        r.setId(rs.getInt("id"));
        r.setUserId(rs.getInt("user_id"));
        r.setReportDate(rs.getDate("report_date"));
        r.setType(rs.getString("report_type"));
        r.setDescription(rs.getString("description"));
        r.setFilePath(rs.getString("file_path"));
        return r;
    }
}
