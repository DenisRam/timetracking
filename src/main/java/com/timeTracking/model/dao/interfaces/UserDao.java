package com.timeTracking.model.dao.interfaces;

import com.timeTracking.model.entities.Account;
import com.timeTracking.model.entities.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {

    int Create(int account_id, String name, String surname, Connection connection);
    User read(int id, Connection connection);
    void update(int account_id, String name, String surname ,Connection connection);
    void delete(User user, Connection connection);
    List<User> getAll(Connection connection);

}
