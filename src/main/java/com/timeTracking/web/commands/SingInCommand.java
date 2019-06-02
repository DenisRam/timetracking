package com.timeTracking.web.commands;

import com.timeTracking.exeptions.DBExeption;
import com.timeTracking.model.entities.Account;
import com.timeTracking.model.entities.User;
import com.timeTracking.web.resourceBundleManagement.MessageManagement;
import com.timeTracking.web.resourceBundleManagement.PageManagement;
import com.timeTracking.web.services.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SingInCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SingInCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String sessionId = session.getId();

        if (session.getAttribute("sessionId") == null) {
            session.setAttribute("sessionId", sessionId);
            LOGGER.info("Session started with id " + sessionId);
        }

        LoginService loginService = new LoginService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Account account = null;
        User currentUser = null;

        try {
            account = loginService.checkAccount(login, password);
        } catch (DBExeption e){
            session.setAttribute("errorMessage", MessageManagement.getProperty(e.getMessage()));
            session.setAttribute("previousPage", "path.page.index");
            LOGGER.error("Cannot get user account from data base", e.getCause());
            return PageManagement.getProperty("path.page.error");
        }

        if(account != null){
            try {
                currentUser = loginService.getUserByAccount(account);
            } catch (DBExeption e){
                session.setAttribute("errorMessage", MessageManagement.getProperty(e.getMessage()));
                session.setAttribute("previousPage", "path.page.index");
                LOGGER.error("Cannot get user from data base", e.getCause());
                return PageManagement.getProperty("path.page.error");
            }
            LOGGER.info("User was found");
        } else {
            LOGGER.info("User was not found");
            request.setAttribute("errorLogin", true);
            return PageManagement.getProperty("path.page.index");
        }

        if (currentUser != null && currentUser.getUserRole().equals("ADMIN")){
            return PageManagement.getProperty("path.page.adminPage");
        } else if(currentUser != null && currentUser.getUserRole().equals("USER")){
            return PageManagement.getProperty("path.page.userPage");
        } else {
            return null;
        }




    }
}
