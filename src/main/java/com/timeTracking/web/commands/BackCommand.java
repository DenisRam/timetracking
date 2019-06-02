package com.timeTracking.web.commands;

import com.timeTracking.web.resourceBundleManagement.PageManagement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BackCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(BackCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String page = (String) session.getAttribute("sessionId");
        if(!session.getId().equals(session.getAttribute("sessionId")) && page !=null){
            LOGGER.info("Session " + session.getId() + " was finish");
            return PageManagement.getProperty("path.page.index");
        }

        String previousPage = (String) session.getAttribute("previousPage");
        LOGGER.info("Go to previous page " + PageManagement.getProperty(previousPage) + " from error page");
        session.removeAttribute("previousPage");
        session.removeAttribute("errorMessage");
        return PageManagement.getProperty(previousPage);
    }
}
