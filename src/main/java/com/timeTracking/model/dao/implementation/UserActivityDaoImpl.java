package com.timeTracking.model.dao.implementation;

import com.timeTracking.exeptions.DBExeption;
import com.timeTracking.model.dao.interfaces.UserActivityDao;
import com.timeTracking.model.entities.UserActivity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserActivityDaoImpl implements UserActivityDao {
    private static final Logger LOGGER = LogManager.getLogger(UserActivityDaoImpl.class);

    @Override
    public int Create(int userId, int activityId, Date startDateOfActivity, Date finishDateOfActivity, Connection connection) {
        String sql = "INSERT INTO user_activities (user_id, activity_id, start_of_activity, finish_of_activity VALUES (?, ?, ?, ?)";
        int id = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, activityId);
            preparedStatement.setDate(3, startDateOfActivity);
            preparedStatement.setDate(4, finishDateOfActivity);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if(resultSet.next()){
                id = resultSet.getInt(1);
            }
        }catch (SQLException e){
            LOGGER.error("Unable to create user activity in database", e.getCause());
            throw new DBExeption("message.error.userActivity");
        }
        return id;
    }

    @Override
    public UserActivity read(int id, Connection connection) {
        UserActivity userActivity = new UserActivity();
        String sql = "SELECT * FROM user_activities WHERE id_user_activity=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                userActivity = new UserActivity.Builder()
                        .setId(resultSet.getInt("id_user_activity"))
                        .setUserId(resultSet.getInt("user_id"))
                        .setActivityId(resultSet.getInt("activity_id"))
                        .setStartDateOfActivity(resultSet.getDate("start_of_activity"))
                        .setFinishDateOfActivity(resultSet.getDate("finish_of_activity"))
                        .setActivityStatus(resultSet.getString("activity_status"))
                        .build();
            }
        }catch (SQLException e){
            LOGGER.error("Unable to read user activity in database", e.getCause());
            throw new DBExeption("message.error.userActivity");
        }
        return userActivity;
    }

    @Override
    public void update(int userActivityId, int userId, int activityId, Date startDateOfActivity, Date finishDateOfActivity, String status, Connection connection) {
        String sql = "UPDATE user_activities SET user_id=?, activity_id=?, start_of_activity=?, finish_of_activity=?, activity_status=? WHERE id_user_activity=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, activityId);
            preparedStatement.setDate(3, startDateOfActivity);
            preparedStatement.setDate(4, finishDateOfActivity);
            preparedStatement.setString(5, status);
            preparedStatement.setInt(6, userActivityId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Unable to update user activity in database", e.getCause());
            throw new DBExeption("message.error.userActivity");
        }
    }

    @Override
    public void delete(UserActivity userActivity, Connection connection) {
        String sql = "DELETE FROM user_activities WHERE id_user_activity=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, userActivity.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Unable to delete user activity in database", e.getCause());
            throw new DBExeption("message.error.userActivity");
        }
    }

    @Override
    public List<UserActivity> getAll(Connection connection) {
        List<UserActivity> list = new ArrayList<>();
        String sql = "SELECT * FROM user_activities ORDER BY id_user_activity";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                list.add(new UserActivity.Builder()
                        .setId(resultSet.getInt("id_user_activity"))
                        .setUserId(resultSet.getInt("user_id"))
                        .setActivityId(resultSet.getInt("activity_id"))
                        .setStartDateOfActivity(resultSet.getDate("start_of_activity"))
                        .setFinishDateOfActivity(resultSet.getDate("finish_of_activity"))
                        .setActivityStatus(resultSet.getString("activity_status"))
                        .build());
            }
        }catch (SQLException e){
            LOGGER.error("Unable to read users activities in database", e.getCause());
            throw new DBExeption("message.error.userActivity");
        }
        return list;
    }
}
