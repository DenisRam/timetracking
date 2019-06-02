package com.timeTracking.model.dao.implementation;

import com.timeTracking.exeptions.DBExeption;
import com.timeTracking.model.dao.interfaces.AccountDao;
import com.timeTracking.model.entities.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private static final Logger LOGGER = LogManager.getLogger(AccountDaoImpl.class);
    @Override
    public int Create(String login, String password, Connection connection) {
        String sql = "INSERT into accounts (login, password) VALUES (?,?)";
        int id = 0;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if(resultSet.next()){
                id = resultSet.getInt(1);
            }
        }catch (SQLException e){
            LOGGER.error("Unable to create account in database", e.getCause());
            throw new DBExeption("message.error.account");
        }

        return id;
    }

    @Override
    public Account read(int accountId, Connection connection) {
        Account account = new Account();
        String sql = "SELECT * FROM accounts WHERE id_account=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                account = new Account.Builder().
                        setId(resultSet.getInt("id_account")).
                        setLogin(resultSet.getString("login")).
                        setPassword(resultSet.getString("password")).
                        build();
            }
        }catch (SQLException e){
            LOGGER.error("Unable to read account from database", e.getCause());
            throw  new DBExeption("message.error.account");
        }
        return account;
    }

    @Override
    public void update(int accoutId, String login, String password, Connection connection) {
        String sql = "UPDATE accounts SET login=?, password=? WHERE id_account=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, accoutId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Unable to update account in database",e.getCause());
            throw new DBExeption("message.error.account");
        }
    }

    @Override
    public void delete(Account account, Connection connection) {
        String sql = "DELETE FROM accounts WHERE id_account=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,account.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Unable to delete account from database",e.getCause());
            throw new DBExeption("message.error.account");
        }
    }

    @Override
    public List<Account> getAll(Connection connection) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts ORDER BY id_account";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                list.add(new Account.Builder()
                        .setId(resultSet.getInt("id_account"))
                        .setLogin(resultSet.getString("login"))
                        .setPassword(resultSet.getString("password"))
                        .build());
            }
        }catch (SQLException e){
            LOGGER.error("Unable to read accounts from database",e.getCause());
            throw new DBExeption("message.error.account");
        }
        return list;
    }
}
