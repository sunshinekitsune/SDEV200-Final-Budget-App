package com.ivytech.sdev200.budget_app.model;

import java.util.ArrayList;
import java.util.List;

public class MonthlyBudget {
    private String monthYear;
    private List<IncomeItem> incomeItems = new ArrayList<>();
    private List<ExpenseAllocation> expenseAllocations = new ArrayList<>();

    public MonthlyBudget() {
        this.monthYear = "October 2026";
    }

    public double getTotalIncome() {
        return incomeItems.stream().mapToDouble(BudgetItem::getAmount).sum();
    }

    public double getTotalAllocated() {
        return expenseAllocations.stream().mapToDouble(BudgetItem::getAmount).sum();
    }

    public double getUnassignedBalance() {
        return getTotalIncome() - getTotalAllocated();
    }

    public boolean isZeroBased() {
        return Math.abs(getUnassignedBalance()) < 0.001 && getTotalIncome() > 0;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public List<IncomeItem> getIncomeItems() {
        return incomeItems;
    }

    public void setIncomeItems(List<IncomeItem> incomeItems) {
        this.incomeItems = incomeItems;
    }

    public List<ExpenseAllocation> getExpenseAllocations() {
        return expenseAllocations;
    }

    public void setExpenseAllocations(List<ExpenseAllocation> expenseAllocations) {
        this.expenseAllocations = expenseAllocations;
    }
}
