package com.timeTracking.model.dao;

import com.timeTracking.model.dao.implementation.ActivityDaoImpl;
import com.timeTracking.model.dao.implementation.UserActivityDaoImpl;
import com.timeTracking.model.dao.implementation.UserDaoImpl;
import com.timeTracking.model.dao.interfaces.AccountDao;
import com.timeTracking.model.dao.implementation.AccountDaoImpl;
import com.timeTracking.model.dao.interfaces.ActivityDao;
import com.timeTracking.model.dao.interfaces.UserActivityDao;
import com.timeTracking.model.dao.interfaces.UserDao;

public class DaoFactory {

        public static AccountDao getAccountDao(){
            return new AccountDaoImpl();
        }

        public static ActivityDao getActivityDao() {
            return new ActivityDaoImpl();
        }

        public static UserActivityDao getUserActivityDao(){
            return new UserActivityDaoImpl();
        }

        public static UserDao getUserDao(){
            return new UserDaoImpl();
        }


}
