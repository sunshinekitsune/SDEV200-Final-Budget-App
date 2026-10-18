package com.ivytech.sdev200.budget_app.controller;

import com.ivytech.sdev200.budget_app.model.CategoryType;
import com.ivytech.sdev200.budget_app.model.ExpenseAllocation;
import com.ivytech.sdev200.budget_app.model.IncomeItem;
import com.ivytech.sdev200.budget_app.model.User;
import com.ivytech.sdev200.budget_app.service.BudgetStorageService;
import com.ivytech.sdev200.budget_app.service.BudgetValidator;
import com.ivytech.sdev200.budget_app.service.PasswordUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BudgetController {

    private final BudgetStorageService storageService;
    private final BudgetValidator validator;

    public BudgetController(BudgetStorageService storageService, BudgetValidator validator) {
        this.storageService = storageService;
        this.validator = validator;
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        model.addAttribute("budget", user.getBudget());
        model.addAttribute("categories", CategoryType.values());
        model.addAttribute("validation", validator.validate(user.getBudget()));
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        if (session.getAttribute("currentUser") != null) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        var userOpt = storageService.loadUser(username);
        if (userOpt.isPresent()
                && PasswordUtils.verify(
                        password, userOpt.get().getSalt(), userOpt.get().getPasswordHash())) {
            session.setAttribute("currentUser", userOpt.get());
            return "redirect:/";
        }
        model.addAttribute("error", "Invalid username or password.");
        return "login";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        if (username.isBlank() || password.isBlank()) {
            model.addAttribute("error", "username and password cannot be blank.");
            return "login";
        }
        if (storageService.userExists(username)) {
            model.addAttribute("error", "Username already taken.");
            return "login";
        }

        String salt = PasswordUtils.generateSalt();
        String hash = PasswordUtils.hashPassword(password, salt);
        var newUser = new User(username.trim(), hash, salt);

        storageService.saveUser(newUser);
        session.setAttribute("currentUser", newUser);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/income/add")
    public String addIncome(
            @RequestParam String name, @RequestParam double amount, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user != null && amount > 0 && !name.isBlank()) {
            user.getBudget().getIncomeItems().add(new IncomeItem(name.trim(), amount));
            storageService.saveUser(user);
        }
        return "redirect:/";
    }

    @PostMapping("/income/delete")
    public String deleteIncome(@RequestParam String id, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            user.getBudget().getIncomeItems().removeIf(item -> item.getId().equals(id));
            storageService.saveUser(user);
        }
        return "redirect:/";
    }

    @PostMapping("/expense/add")
    public String addExpense(
            @RequestParam String name,
            @RequestParam double amount,
            @RequestParam CategoryType category,
            HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user != null && amount > 0 && !name.isBlank()) {
            user.getBudget()
                    .getExpenseAllocations()
                    .add(new ExpenseAllocation(name.trim(), amount, category));
            storageService.saveUser(user);
        }
        return "redirect:/";
    }

    @PostMapping("/expense/delete")
    public String deleteExpense(@RequestParam String id, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            user.getBudget().getExpenseAllocations().removeIf(item -> item.getId().equals(id));
            storageService.saveUser(user);
        }
        return "redirect:/";
    }
}
