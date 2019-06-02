package com.timeTracking.model.entities;

import java.io.Serializable;
import java.sql.Date;


public class UserActivity implements Serializable {

    private int id;
    private int userId;
    private int activityId;
    private Date startDateOfActivity;
    private Date finishDateOfActivity;
    private String status;

    public UserActivity(){
    }

    private UserActivity(Builder builder){
        this.id = builder.id;
        this.userId = builder.userId;
        this.activityId = builder.activityId;
        this.startDateOfActivity = builder.startDateOfActivity;
        this.finishDateOfActivity = builder.finishDateOfActivity;
        this.status = builder.activityStatus;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getActivityId() {
        return activityId;
    }

    public Date getStartDateOfActivity() {
        return startDateOfActivity;
    }

    public Date getFinishDateOfActivity() {
        return finishDateOfActivity;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserActivity that = (UserActivity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public java.lang.String toString() {
        final StringBuffer stringBuffer = new StringBuffer("UserActivity: ");
        stringBuffer.append("id = ").append(id);
        stringBuffer.append(", userId = ").append(userId);
        stringBuffer.append(", activityId = ").append(activityId);
        stringBuffer.append(", startDateOfActivity = ").append(startDateOfActivity);
        stringBuffer.append(", finishDateOfActivity = ").append(finishDateOfActivity);
        stringBuffer.append(", status = ").append(status);
        return stringBuffer.toString();

    }

    public static class Builder{
        private int id;
        private int userId;
        private int activityId;
        private Date startDateOfActivity;
        private Date finishDateOfActivity;
        private String activityStatus;;

        public Builder setId(int id){
            this.id = id;
            return this;
        }

        public Builder setUserId(int userId){
            this.userId = userId;
            return this;
        }

        public Builder setActivityId(int activityId){
            this.activityId = activityId;
            return this;
        }

        public Builder setStartDateOfActivity(Date startDateOfActivity){
            this.startDateOfActivity = startDateOfActivity;
            return this;
        }

        public Builder setFinishDateOfActivity(Date finishDateOfActivity){
            this.finishDateOfActivity = finishDateOfActivity;
            return this;
        }
        public Builder setActivityStatus(String activityStatus){
            this.activityStatus = activityStatus;
            return this;
        }

        public UserActivity build(){
            return new UserActivity(this);
        }
    }
}
