package com.timeTracking.model.dao.implementation;

import com.timeTracking.exeptions.DBExeption;
import com.timeTracking.model.dao.interfaces.UserDao;
import com.timeTracking.model.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public int Create(int account_id, String name, String surname, Connection connection) {
        String sql = "INSERT INTO users (account_id, name, surname) VALUES (?, ?, ?)";
        int id = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, account_id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if(resultSet.next()){
                id = resultSet.getInt(1);
            }
        }catch (SQLException e){
            LOGGER.error("Unable to create user in database", e.getCause());
            throw new DBExeption("message.error.user");
        }
        return id;
    }

    @Override
    public User read(int id, Connection connection) {
        User user = new User();
        String sql = "SELECT * FROM users WHERE id_user=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User.Builder()
                        .setId(resultSet.getInt("id_user"))
                        .setAccountId(resultSet.getInt("account_id"))
                        .setName(resultSet.getString("name"))
                        .setSurName(resultSet.getString("surname"))
                        .setUserRole(resultSet.getString("role"))
                        .build();
            }
        }catch (SQLException e){
            LOGGER.error("Unable to read user in database", e.getCause());
            throw new DBExeption("message.error.user");
        }
        return user;
    }

    @Override
    public void update(int id, String name, String surname, Connection connection) {
        String sql = "UPDATE users SET name=?, surname=? WHERE id_user=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Unable to update user in database", e.getCause());
            throw new DBExeption("message.error.user");
        }
    }

    @Override
    public void delete(User user, Connection connection) {
        String sql = "DELETE FROM users WHERE id_user=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Unable to delete user in database", e.getCause());
            throw new DBExeption("message.error.user");
        }
    }

    @Override
    public List<User> getAll(Connection connection) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY id_user";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                list.add(new User.Builder().
                        setId(resultSet.getInt("id_user")).
                        setAccountId(resultSet.getInt("account_id")).
                        setName(resultSet.getString("name")).
                        setSurName(resultSet.getString("surname")).
                        setUserRole(resultSet.getString("role")).
                        build());
            }
        }catch (SQLException e){
            LOGGER.error("Unable to read users in database", e.getCause());
            throw new DBExeption("message.error.user");
        }
        return list;
    }
}
