package com.timeTracking.web.controller;

import com.timeTracking.web.commands.Command;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet implements Servlet {
    RequestParameterHelper requestParameterHelper = RequestParameterHelper.getInstance();

    public Controller() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }

    private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = (Command) requestParameterHelper.getCommand(request);

        String page = command.execute(request, response);
        request.getRequestDispatcher(page).forward(request, response);
    }

}
