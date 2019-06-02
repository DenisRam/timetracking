package com.timeTracking.model.dao.interfaces;

import com.timeTracking.model.entities.Activity;

import java.sql.Connection;
import java.util.List;

public interface ActivityDao {
    int Create(String activityTitle, String activityDescription, Connection connection);
    Activity read(int id, Connection connection);
    void update(int id, String activityTitle, String activityDescription, Connection connection);
    void delete(Activity activity, Connection connection);
    List<Activity> getAll(Connection connection);
}
