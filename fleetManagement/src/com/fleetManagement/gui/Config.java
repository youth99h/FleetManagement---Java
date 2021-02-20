package com.fleetManagement.gui;

//≈‰÷√

public class Config {
    private static int width;
    private static int height;

    private static String adminName;
    private static String adminPassword;

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        Config.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Config.height = height;
    }

    public static String getAdminName() {
        return adminName;
    }

    public static void setAdminName(String adminName) {
        Config.adminName = adminName;
    }

    public static String getAdminPassword() {
        return adminPassword;
    }

    public static void setAdminPassword(String adminPassword) {
        Config.adminPassword = adminPassword;
    }
}
