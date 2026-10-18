package com.ivytech.sdev200.budget_app.model;

import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String passwordHash;
    private String salt;
    private MonthlyBudget budget;

    public User() {
        this.id = UUID.randomUUID().toString();
        this.budget = new MonthlyBudget();
    }

    public User(String username, String passwordHash, String salt) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.budget = new MonthlyBudget();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public MonthlyBudget getBudget() {
        return budget;
    }

    public void setBudget(MonthlyBudget budget) {
        this.budget = budget;
    }
}
