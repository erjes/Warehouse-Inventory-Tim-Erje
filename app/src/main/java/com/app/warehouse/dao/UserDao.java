package com.app.warehouse.dao;

import com.app.warehouse.model.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    User findById(int id) throws SQLException;

    User findByUsername(String username) throws SQLException;

    List<User> findAll() throws SQLException;

    List<User> findByRole(int roleId) throws SQLException;

    int create(User user) throws SQLException;

    int update(User user) throws SQLException;

    int delete(int id) throws SQLException;
}
