package com.ivytech.sdev200.budget_app.model;

public enum CategoryType {
    HOUSING("Housing"),
    UTILITIES("Utilities"),
    FOOD("Food"),
    TRANSPORTATION("Transportation"),
    DEBT("Debt"),
    SAVINGS("Savings"),
    DISCRETIONARY("Discretionary / Fun"),
    OTHER("Other");

    private final String displayName;

    CategoryType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
