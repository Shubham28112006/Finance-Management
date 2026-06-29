package com.example.finance.resource;

import com.example.finance.model.ExpenseModel;
import com.example.finance.repositary.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/form")
    public String showExpenseForm() {
        return "Expense";
    }

    @PostMapping("/add")
    public String addExpense(
            @RequestParam String category,
            @RequestParam Double amount,
            @RequestParam String description,
            @RequestParam String date,
            Model model) {

        ExpenseModel expense = new ExpenseModel();


        expense.setAmount(amount);
        expense.setDescription(description);
        expense.setDate(LocalDate.parse(date));

        expenseRepository.save(expense);

        model.addAttribute("msg",
                "Expense Added Successfully!");

        return "Expense";
    }
}