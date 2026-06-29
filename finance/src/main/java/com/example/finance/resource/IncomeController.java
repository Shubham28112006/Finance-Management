package com.example.finance.resource;

import com.example.finance.model.IncomeModel;
import com.example.finance.repositary.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeRepository incomeRepository;

    @RequestMapping("/addin")
    public String addIncome(
            @RequestParam String source,
            @RequestParam Double amount,
            @RequestParam String date,
            Model model) {

        IncomeModel income = new IncomeModel();
        income.setSource(source);
        income.setAmount(amount);
        income.setDate(LocalDate.parse(date));

        incomeRepository.save(income);

        model.addAttribute(
                "msg",
                "Income Added Successfully!"
        );

        return "UserDashboard";
    }
}
