package com.example.project2;
public class UserContext {
    private static UserContext instance;
    private String currentUsername;

    private UserContext() {
        // Private constructor to prevent instantiation
    }

    public static UserContext getInstance() {
        if (instance == null) {
            instance = new UserContext();
        }
        return instance;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }
}