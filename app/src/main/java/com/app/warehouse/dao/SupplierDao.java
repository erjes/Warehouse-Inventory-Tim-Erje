package com.app.warehouse.dao;

import com.app.warehouse.model.Supplier;
import java.sql.SQLException;
import java.util.List;

public interface SupplierDao {

    Supplier findById(int id) throws SQLException;

    List<Supplier> findAll() throws SQLException;

    int create(Supplier supplier) throws SQLException;

    int update(Supplier supplier) throws SQLException;

    int delete(int id) throws SQLException;
}
