package com.timeTracking.web.controller;

import com.timeTracking.web.commands.BackCommand;
import com.timeTracking.web.commands.ChangeLocaleComand;
import com.timeTracking.web.commands.Command;
import com.timeTracking.web.commands.SingInCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class RequestParameterHelper {

    private static RequestParameterHelper instance = null;
    private HashMap<String, Command> commands = new HashMap<>();

    private RequestParameterHelper() {
        commands.put("changeLocale", new ChangeLocaleComand());
        commands.put("singIn", new SingInCommand());
        commands.put("back", new BackCommand());
//        commands.put("cancelCreatePublication", new CancelCreatePublicationCommand());
//        commands.put("cancelEditPublication", new CancelEditPublicationCommand());
//        commands.put("cancelWievSubscription", new CancelViewSubscriptionCommand());
//        commands.put("cancelWievAboutBill", new CancelWievAboutBillComand());
//        commands.put("createPublication", new CreatePublicationCommand());
//        commands.put("createSubscription", new CreateSubscriptionCommand());
//        commands.put("cancelCreateSubscription", new CancelCreateSubscriptionCommand()
    }


    public Command getCommand(HttpServletRequest request) {
        String actionName = request.getParameter("command");
        if (actionName == null) {
            actionName = "noCommand";
        }
        return commands.get(actionName);
    }

    public static RequestParameterHelper getInstance() {
        if (instance == null) {
            instance = new RequestParameterHelper();
        }
        return instance;
    }
}
