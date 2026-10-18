package com.ivytech.sdev200.budget_app.model;

import java.util.UUID;

public abstract class BudgetItem {
    private String id;
    private String name;
    private double amount;

    protected BudgetItem() {
        this.id = UUID.randomUUID().toString();
    }

    protected BudgetItem(String name, double amount) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
