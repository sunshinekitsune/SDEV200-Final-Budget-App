<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Zero-Based Budget</title>
</head>
<body style="font-family: sans-serif; margin: 20px;">
<div>
    <h2>Zero-Based Budget Manager</h2>
    <p>
        Logged in as:
        <strong>${user.username}</strong>
        |
        <a href="/logout">Logout</a>
    </p>
    <hr/>
</div>
<!-- Equation Metrics -->
<div style="display: flex; gap: 30px; margin: 15px 0;">
    <div>
        <strong>Total Income:</strong>
        $
        <fmt:formatNumber value="${budget.totalIncome}" minFractionDigits="2" maxFractionDigits="2"/>
    </div>
    <div>
        <strong>Total Allocated:</strong>
        $
        <fmt:formatNumber value="${budget.totalAllocated}" minFractionDigits="2" maxFractionDigits="2"/>
    </div>
    <div>
        <strong>Unassigned Balance:</strong>
        $
        <fmt:formatNumber value="${budget.unassignedBalance}" minFractionDigits="2" maxFractionDigits="2"/>
    </div>
</div>
<!-- Messages -->
<div style="border: 1px solid #999; padding: 10px; margin-bottom: 20px; background: #fafafa;">
    <strong>Status:</strong>
    <c:forEach var="msg" items="${validation.messages()}">
        <div>${msg}</div>
    </c:forEach>
</div>
<!-- Forms and Tables -->
<div style="display: flex; gap: 40px;">
    <!-- Income -->
    <div style="flex: 1;">
        <h3>1. Income Sources</h3>
        <form action="/income/add" method="post">
            <input type="text" name="name" placeholder="e.g. Paycheck" required/>
            <input type="number" step="0.01" min="0.01" name="amount" placeholder="0.00" required/>
            <button type="submit">Add Income</button>
        </form>
        <table border="1" cellpadding="6" cellspacing="0" style="width: 100%; margin-top: 10px;">
            <thead>
            <tr>
                <th>Source</th>
                <th>Amount</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${budget.incomeItems}">
                <tr>
                    <td>${item.name}</td>
                    <td>
                        $
                        <fmt:formatNumber value="${item.amount}" minFractionDigits="2" maxFractionDigits="2"/>
                    </td>
                    <td>
                        <form action="/income/delete" method="post" style="margin: 0;">
                            <input type="hidden" name="id" value="${item.id}"/>
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty budget.incomeItems}">
                <tr>
                    <td colspan="3">No income added yet.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
    <!-- Expenses -->
    <div style="flex: 1;">
        <h3>2. Expense Allocations</h3>
        <form action="/expense/add" method="post">
            <input type="text" name="name" placeholder="e.g. Rent" required/>
            <input type="number" step="0.01" min="0.01" name="amount" placeholder="0.00" required/>
            <select name="category" required>
                <c:forEach var="cat" items="${categories}">
                    <option value="${cat}">${cat.displayName}</option>
                </c:forEach>
            </select>
            <button type="submit">Assign</button>
        </form>
        <table border="1" cellpadding="6" cellspacing="0" style="width: 100%; margin-top: 10px;">
            <thead>
            <tr>
                <th>Category</th>
                <th>Expense</th>
                <th>Amount</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${budget.expenseAllocations}">
                <tr>
                    <td>${item.category.displayName}</td>
                    <td>${item.name}</td>
                    <td>
                        $
                        <fmt:formatNumber value="${item.amount}" minFractionDigits="2" maxFractionDigits="2"/>
                    </td>
                    <td>
                        <form action="/expense/delete" method="post" style="margin: 0;">
                            <input type="hidden" name="id" value="${item.id}"/>
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty budget.expenseAllocations}">
                <tr>
                    <td colspan="4">No expenses assigned yet.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>