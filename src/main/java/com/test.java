package com;

import com.timeTracking.exeptions.DBExeption;
import com.timeTracking.model.dao.implementation.AccountDaoImpl;
import com.timeTracking.model.dao.implementation.UserDaoImpl;
import com.timeTracking.model.dao.interfaces.AccountDao;
import com.timeTracking.model.entities.Account;
import com.timeTracking.model.entities.User;
import com.timeTracking.model.util.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class test {

    public static void main(String[] args) throws Throwable {
//        UserDaoImpl userDao = new UserDaoImpl();
//        Connection connection = ConnectionPool.getConnection(true);
//
//        AccountDao accountDao = new AccountDaoImpl();
//        System.out.println(accountDao.read(1, connection));
//        System.out.println(accountDao.read(2, connection));
        DBExeption e = new DBExeption();
        throw e.getCause();






    }
}
