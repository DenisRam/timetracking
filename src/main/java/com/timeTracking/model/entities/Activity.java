package com.timeTracking.model.entities;

import java.io.Serializable;

public class Activity implements Serializable {

    private int id;
    private String activityTitle;
    private String activityDescription;

    public Activity() {
    }
    private Activity(Builder builder){
        this.id = builder.id;
        this.activityTitle = builder.activityTitle;
        this.activityDescription = builder.activityDescription;
    }

    public int getId() {
        return id;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return id == activity.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuffer stringBuffer = new StringBuffer("Activity: ");
        stringBuffer.append("id = ").append(id);
        stringBuffer.append(", title = ").append(activityTitle);
        stringBuffer.append(", description = ").append(activityDescription);
        return stringBuffer.toString();
    }

    public static class Builder{
        private int id;
        private String activityTitle;
        private String activityDescription;

        public Builder setId(int id){
            this.id = id;
            return this;
        }

        public Builder setActivityTitle(String activityTitle){
            this.activityTitle = activityTitle;
            return this;
        }

        public Builder setActivityDescriptoin(String activityDescription){
            this.activityDescription = activityDescription;
            return this;
        }

        public Activity build(){
            return new Activity(this);
        }
    }
}
