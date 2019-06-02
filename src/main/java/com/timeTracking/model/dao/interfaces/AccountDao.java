package com.timeTracking.model.dao.interfaces;

import com.timeTracking.model.entities.Account;

import java.sql.Connection;
import java.util.List;

public interface AccountDao {
    int Create(String login, String password, Connection connection);
    Account read(int id, Connection connection);
    void update(int id, String login, String password,Connection connection);
    void delete(Account account, Connection connection);
    List<Account> getAll(Connection connection);
}
