package com.example.finance.repositary;

import com.example.finance.model.ExpenseModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseModel,Integer>{

    @Query("SELECT COALESCE(SUM(e.amount),0) FROM ExpenseModel e")
    Double getTotalExpense();

    @Query("select u from ExpenseModel as u where u.id=:id")
    public ExpenseModel ShowAllDetails(@RequestParam("id") Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM ExpenseModel u WHERE u.id=:id")
    void deleteById(@RequestParam("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE ExpenseModel u set u.amount=:amount,u.description=:description,u.date=:date where u.id=:id")
    void UpdateRecord(
            @RequestParam("tid") Long id,
            @RequestParam("tam") Double amount,
            @RequestParam("tde") String description,
            @RequestParam("tda") LocalDate date);



    @Query("SELECT e FROM ExpenseModel e WHERE e.date BETWEEN :startDate AND :endDate ORDER BY e.date")
    List<ExpenseModel> getExpenseReport(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate);

}

