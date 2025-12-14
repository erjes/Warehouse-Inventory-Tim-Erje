package com.app.warehouse.dao;

import com.app.warehouse.model.Report;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ReportDao {

    Report findById(int id) throws SQLException;

    List<Report> findAll() throws SQLException;

    List<Report> findByDateRange(Date start, Date end) throws SQLException;

    int create(Report report) throws SQLException;

    int update(Report report) throws SQLException;

    int delete(int id) throws SQLException;
}
