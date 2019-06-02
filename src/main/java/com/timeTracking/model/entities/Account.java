package com.timeTracking.model.entities;

import java.io.Serializable;

public class Account implements Serializable {
    private int id;
    private String login;
    private String password;

    public Account (){
    }

    private Account(Builder builder){
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuffer stringBuffer = new StringBuffer("Account: ");
        stringBuffer.append("id = ").append(id);
        stringBuffer.append(", login = ").append(login);
        stringBuffer.append(", password = ").append(password);
        return stringBuffer.toString();
    }

    public static class Builder {
        private int id;
        private String login;
        private String password;

        public Builder setId(int id){
            this.id = id;
            return this;
        }
        public Builder setLogin(String login){
            this.login = login;
            return this;
        }
        public Builder setPassword(String password){
            this.password = password;
            return this;
        }
        public Account build(){
            return new Account(this);
        }


    }
}
