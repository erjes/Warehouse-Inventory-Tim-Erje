package com.app.warehouse.dao;

import com.app.warehouse.model.Item;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemDao {

    int create(Item item) throws SQLException;

    Item findById(int id) throws SQLException;

    List<Item> findAll() throws SQLException;

    List<Item> findByWarehouse(int warehouseId) throws SQLException;

    int update(Item item) throws SQLException;

    int updateQuantity(int itemId, int newQuantity, Connection conn) throws SQLException;

    int updateQuantityDelta(int itemId, int delta, Connection conn) throws SQLException;

    int delete(int id) throws SQLException;
}
