package com.example.finance.resource;

import com.example.finance.model.ExpenseModel;
import com.example.finance.model.IncomeModel;
import com.example.finance.model.UserModel;
import com.example.finance.repositary.ExpenseRepository;
import com.example.finance.repositary.IncomeRepository;
import com.example.finance.repositary.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/finance")
public class UserController {

    @Autowired
    UserRepo ur;

    @Autowired
    IncomeRepository ir;

    @Autowired
    ExpenseRepository er;


    @RequestMapping(value = "/")
    public String getHome() {
        return "login";
    }

    @RequestMapping(value = "/checking")
    public ModelAndView checkLogin(@RequestParam("tmail") String tmail,
                                   @RequestParam("tpass") String tpass,
                                   @RequestParam("sub") String sub) {

        ModelAndView mv = new ModelAndView();

        if (sub.equals("Login")) {

            UserModel u = ur.findByEmailAndPassword(tmail, tpass);

            if (u != null) {

                mv.setViewName("UserDashboard");
            }

        } else if (sub.equals("Register")) {

            mv.setViewName("Register");

        } else {

            mv.setViewName("AdminLogin");

        }

        return mv;
    }

    @RequestMapping(value = "/add")
    public String add(@RequestParam("tname") String tname, @RequestParam("tmail") String tmail, @RequestParam("tphone") String tphone, @RequestParam("tpass") String tpass) {
        String file = "";

        UserModel um = new UserModel(tname,tpass,tphone,tmail);

        ur.save(um);


        return "login";
    }

    @RequestMapping(value = "/AdminCheck")
    public String AdminCheck(@RequestParam("tmail")  String tmail,@RequestParam("tpass") String tpass){
        String file="";

        if(tmail.equals("finance@gamil.com") && tpass.equals("finance")){
            file="AdminDashboard";
        }else{
            file="AdminLogin";
        }
        return file;
    }


    @RequestMapping("/show")
    public ModelAndView show(Model model, @RequestParam("sub") String sub) {

        ModelAndView mv = new ModelAndView();

        if (sub.equals("Income")) {

            mv.setViewName("Income");

        }
        else if (sub.equals("Expense")) {

            mv.setViewName("Expense");

        }
        else if (sub.equals("Reports")) {

            mv.setViewName("Reports");

        }
        else if ("ShowIncome".equals(sub)) {

            List<IncomeModel> list = ir.findAll();

            double totalIncome = 0;
            for (IncomeModel income : list) {
                totalIncome += income.getAmount();
            }

            model.addAttribute("ulist", list);
            model.addAttribute("totalIncome", totalIncome);

            mv.setViewName("ShowIncome");
        }
        else if ("ShowExpense".equals(sub)) {

            List<ExpenseModel> list = er.findAll();

            double totalIncome = 0;
            for (ExpenseModel income : list) {
                totalIncome += income.getAmount();
            }

            model.addAttribute("ulist", list);
            model.addAttribute("totalexpense", totalIncome);

            mv.setViewName("ShowExpence");
        }
        else if (sub.equals("Profile")) {

            mv.setViewName("Profile");

        }
        else if (sub.equals("Logout")) {

            mv.setViewName("Login");

        }
        else {


                Double totalIncome = ir.getTotalIncome();
                Double totalExpense = er.getTotalExpense();

                if (totalIncome == null)
                    totalIncome = 0.0;

                if (totalExpense == null)
                    totalExpense = 0.0;

                mv.addObject("totalIncome", totalIncome);
                mv.addObject("totalExpense", totalExpense);
                mv.addObject("balance", totalIncome - totalExpense);

                mv.setViewName("UserDashboard");

            }

        return mv;
    }
}