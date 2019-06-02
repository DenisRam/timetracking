package com.timeTracking.web.services;

import com.timeTracking.exeptions.DBExeption;
import com.timeTracking.model.dao.DaoFactory;
import com.timeTracking.model.dao.interfaces.AccountDao;
import com.timeTracking.model.dao.interfaces.UserDao;
import com.timeTracking.model.entities.Account;
import com.timeTracking.model.entities.User;
import com.timeTracking.model.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class LoginService {
    private static final Logger LOGGER = LogManager.getLogger(LoginService.class);

    private AccountDao accountDao = DaoFactory.getAccountDao();
    private UserDao userDao = DaoFactory.getUserDao();

    public Account checkAccount(String login, String password){
        Connection connection = ConnectionPool.getConnection(true);
        try {
            List<Account> allAccounts = accountDao.getAll(connection);
            for (Account account : allAccounts) {
                if (login.equals(account.getLogin()) && password.equals(account.getPassword())){
                    return account;
                }
            }
        } catch (DBExeption e){
            LOGGER.error("Cannot check account", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return null;
    }

    public User getUserByAccount(Account account){
        Connection connection = ConnectionPool.getConnection(true);
        try {
            List<User> users = userDao.getAll(connection);
            for (User user : users){
                if (account.getId() == user.getAccountId()){
                    return user;
                }
            }
        } catch (DBExeption e){
            LOGGER.error("Cannot get user by accout", e.getCause());
            throw e;
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return null;
    }
}
