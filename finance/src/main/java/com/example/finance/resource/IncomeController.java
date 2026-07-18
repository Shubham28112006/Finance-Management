package com.example.finance.resource;

import com.example.finance.model.ExpenseModel;
import com.example.finance.model.IncomeModel;
import com.example.finance.repositary.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/updateincome")
    public ModelAndView update(Model model, @RequestParam("sub") String sub, @RequestParam("id") Long id){

        ModelAndView mv = new ModelAndView();

        if (sub.equals("Update")) {

            IncomeModel uid = incomeRepository.ShowAllDetails(id);
            model.addAttribute("ulist",uid);
            mv.addObject(model);
            mv.setViewName("UserDashboard");
            return mv;



        } else if (sub.equals("Delete")) {

            incomeRepository.deleteById(id);

            mv.setViewName("");

            return mv;
        }
        return mv;
    }

    @RequestMapping("/updatedetails")
    public String updatedetails(@RequestParam("tid") Long tid,@RequestParam("tso") String tso, @RequestParam("tam") Double tam,@RequestParam("tda") LocalDate tda) {
        incomeRepository.UpdateRecord(tid, tso, tam, tda);
        return "UserDashboard";
    }
}
