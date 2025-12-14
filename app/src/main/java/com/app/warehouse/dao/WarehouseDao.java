package com.app.warehouse.dao;

import com.app.warehouse.model.Warehouse;
import java.sql.SQLException;
import java.util.List;

public interface WarehouseDao {

    Warehouse findById(int id) throws SQLException;

    List<Warehouse> findAll() throws SQLException;

    int create(Warehouse warehouse) throws SQLException;

    int update(Warehouse warehouse) throws SQLException;

    int delete(int id) throws SQLException;
}
