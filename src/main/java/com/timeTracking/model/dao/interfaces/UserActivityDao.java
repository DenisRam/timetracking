package com.timeTracking.model.dao.interfaces;

import com.timeTracking.model.entities.UserActivity;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public interface UserActivityDao {

    int Create(int userId, int activityId, Date startDateOfActivity, Date finishDateOfActivity, Connection connection);
    UserActivity read(int id, Connection connection);
    void update(int userActivityId, int userId, int activityId, Date startDateOfActivity, Date finishDateOfActivity, String status ,Connection connection);
    void delete(UserActivity userActivity, Connection connection);
    List<UserActivity> getAll(Connection connection);

}
