package com.app.warehouse.dao;

import com.app.warehouse.model.Customer;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {

    Customer findById(int id) throws SQLException;

    List<Customer> findAll() throws SQLException;

    int create(Customer customer) throws SQLException;

    int update(Customer customer) throws SQLException;

    int delete(int id) throws SQLException;
}
