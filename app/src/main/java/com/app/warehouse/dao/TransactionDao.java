package com.app.warehouse.dao;

import com.app.warehouse.model.Transaction;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface TransactionDao {

    int create(Transaction transaction, Connection conn) throws SQLException;

    Transaction findById(int id) throws SQLException;

    List<Transaction> findAll() throws SQLException;

    List<Transaction> findByDateRange(Date start, Date end) throws SQLException;

    List<Transaction> findByUser(int userId) throws SQLException;

    List<Transaction> findByItem(int itemId) throws SQLException;
}
