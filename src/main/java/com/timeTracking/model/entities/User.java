package com.timeTracking.model.entities;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private int id;
    private int accountId;
    private String name;
    private String surName;
    private String userRole;

    public User() {
    }
    private User(Builder builder){
        this.id = builder.id;
        this.accountId = builder.accountId;
        this.name = builder.name;
        this.surName = builder.surName;
        this.userRole = builder.userRole;
    }

    public int getId() {
        return id;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getUserRole() {
        return userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuffer stringBuffer = new StringBuffer("User: ");
        stringBuffer.append("id = ").append(id);
        stringBuffer.append(", accountId = ").append(accountId);
        stringBuffer.append(", name = ").append(name);
        stringBuffer.append(", surname = ").append(surName);
        stringBuffer.append(", userRole = ").append(userRole);
        return stringBuffer.toString();
    }

    public static class Builder{

        private int id;
        private int accountId;
        private String name;
        private String surName;
        private String userRole;

        public Builder setId(int id){
            this.id = id;
            return this;
        }

        public Builder setAccountId(int accountId){
            this.accountId = accountId;
            return this;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setSurName(String surName){
            this.surName = surName;
            return this;
        }

        public Builder setUserRole(String userRole){
            this.userRole = userRole;
            return this;
        }

        public User build(){
            return new User(this);
        }

    }
}
