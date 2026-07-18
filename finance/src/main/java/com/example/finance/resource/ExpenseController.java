package com.example.finance.resource;

import com.example.finance.model.ExpenseModel;
import com.example.finance.repositary.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @RequestMapping("/form")
    public String showExpenseForm() {
        return "Expense";
    }

    @PostMapping("/add")
    public String addExpense(
            @RequestParam Double amount,
            @RequestParam String description,
            @RequestParam String date,
            Model model) {

        ExpenseModel expense = new ExpenseModel();

        expense.setAmount(amount);
        expense.setDescription(description);
        expense.setDate(LocalDate.parse(date));

        expenseRepository.save(expense);

        model.addAttribute("msg", "Expense Added Successfully!");

        return "UserDashboard";
    }

        @RequestMapping(value = "/update")
       public ModelAndView update(Model model,@RequestParam("sub") String sub,@RequestParam("id") Long id){

            ModelAndView mv = new ModelAndView();

            if (sub.equals("Update")) {

                ExpenseModel uid = expenseRepository.ShowAllDetails(id);
                model.addAttribute("ulist",uid);
                mv.addObject(model);
                mv.setViewName("UpdateExpenseRecords");
                return mv;



        } else if (sub.equals("Delete")) {

                expenseRepository.deleteById(id);

                mv.setViewName("UserDashboard");

                return mv;
            }
            return mv;
    }

    @RequestMapping("/updatedetails")
    public String updatedetails(@RequestParam("tid") Long tid,@RequestParam("tam") Double tam, @RequestParam("tde") String tde,@RequestParam("tda") LocalDate tda) {
        expenseRepository.UpdateRecord(tid, tam, tde, tda);
        return "UserDashboard";
    }
}