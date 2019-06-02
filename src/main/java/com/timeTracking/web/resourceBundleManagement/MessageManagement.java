package com.timeTracking.web.resourceBundleManagement;

import java.util.ResourceBundle;

public class MessageManagement {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    public MessageManagement(){

    }
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
