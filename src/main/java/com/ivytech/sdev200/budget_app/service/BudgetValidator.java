package com.ivytech.sdev200.budget_app.service;

import com.ivytech.sdev200.budget_app.model.MonthlyBudget;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BudgetValidator {

    public record ValidationResult(
            boolean isBalanced, double unassignedAmount, List<String> messages) {}

    public ValidationResult validate(MonthlyBudget budget) {
        var messages = new ArrayList<String>();
        double income = budget.getTotalIncome();
        double unassigned = budget.getUnassignedBalance();

        if (income <= 0) {
            messages.add("Enter income to begin zero-based budgeting.");
        }
        if (unassigned > 0.001) {
            messages.add(
                    String.format("Under-allocated: $%.2f remaining to give a job.", unassigned));
        } else if (unassigned < -0.001) {
            messages.add(
                    String.format(
                            "Over-allocated: Exceeded total income by $%.2f!",
                            Math.abs(unassigned)));
        } else if (income > 0) {
            messages.add(
                    "Perfect Zero-Based Budget! Income minus allocations equals zero ($0.00).");
        }

        return new ValidationResult(budget.isZeroBased(), unassigned, messages);
    }
}
