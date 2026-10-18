package com.ivytech.sdev200.budget_app.model;

public class ExpenseAllocation extends BudgetItem {
    private CategoryType category;

    public ExpenseAllocation() {
        super();
        this.category = CategoryType.OTHER;
    }

    public ExpenseAllocation(String name, double amount, CategoryType category) {
        super(name, amount);
        this.category = category;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }
}
