package com.timeTracking.web.commands;

import com.timeTracking.web.resourceBundleManagement.PageManagement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocaleComand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ChangeLocaleComand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("sessionId") != null && !session.getId().equals(session.getAttribute("sessionId"))){
            LOGGER.info("Session " + session.getId() + " was finished");
            return PageManagement.getProperty("path.page.login");
        }
        String key = request.getParameter("currentPage");
        String locale = request.getParameter("locale");
        session.setAttribute("locale", locale);
        if (locale.equals("1")){
            LOGGER.info("Locale was changed to RU");
        } else {
            LOGGER.info("Locale was changed to EN");
        }
        return PageManagement.getProperty(key);
    }
}
