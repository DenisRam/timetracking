package com.timeTracking.model.dao.implementation;

import com.timeTracking.exeptions.DBExeption;
import com.timeTracking.model.dao.interfaces.ActivityDao;
import com.timeTracking.model.entities.Activity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityDaoImpl implements ActivityDao {
    private static final Logger LOGGER = LogManager.getLogger(ActivityDaoImpl.class);

    @Override
    public int Create(String activityTitle, String activityDescription, Connection connection) {
        String sql = "INSERT INTO aсctivities (activity_title, activity_description) VALUES(?, ?)" ;
        int id = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, activityTitle);
            preparedStatement.setString(2,activityDescription);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSRT_ID()");
            if(resultSet.next()){
                id = resultSet.getInt(1);
            }
        }catch (SQLException e){
            LOGGER.error("Unable to create activity in database", e.getCause());
            throw new DBExeption("message.error.activity");
        }
        return id;
    }

    @Override
    public Activity read(int id, Connection connection) {
        Activity activity = new Activity();
        String sql = "SELECT activity_title, activity_description FROM aсctivities WHERE id_activity=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                activity = new Activity.Builder().
                        setId(resultSet.getInt("id_activity")).
                        setActivityTitle(resultSet.getString("activity_title")).
                        setActivityDescriptoin(resultSet.getString("activity_description")).
                        build();
            }
        }catch (SQLException e){
            LOGGER.error("Unable to read account from database", e.getCause());
            throw new DBExeption("message.error.activity");
        }
        return activity;
    }

    @Override
    public void update(int id, String activityTitle, String activityDescription, Connection connection) {
        String sql = "UPDATE aсctivities SET activity_title=?, activity_description=? WHERE id_activity=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,activityTitle);
            preparedStatement.setString(2,activityDescription);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Unable to update account in database", e.getCause());
            throw new DBExeption("message.error.activity");
        }
    }



    @Override
    public void delete(Activity activity, Connection connection) {
        String sql = "DELETE FROM aсctivities WHERE id_activity=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,activity.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            LOGGER.error("Unable to delete aсctivity from database", e.getCause());
            throw new DBExeption("message.error.activity");
        }
    }

    @Override
    public List<Activity> getAll(Connection connection) {
        List<Activity> list = new ArrayList<>();
        String sql = "SELECT * FROM aсctivities ORDER BY id_activity";
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                list.add(new Activity.Builder()
                        .setId(resultSet.getInt("id_activity"))
                        .setActivityTitle(resultSet.getString("activity_title"))
                        .setActivityDescriptoin(resultSet.getString("activity_description"))
                        .build());
            }
        }catch (SQLException e){
            LOGGER.error("Unable to read aсctivities from database", e.getCause());
            throw new DBExeption("message.error.activity");
        }
        return list;
    }
}
