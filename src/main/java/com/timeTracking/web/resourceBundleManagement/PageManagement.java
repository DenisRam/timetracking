package com.timeTracking.web.resourceBundleManagement;

import java.util.ResourceBundle;

public class PageManagement {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("pages");
    private PageManagement() {

    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
