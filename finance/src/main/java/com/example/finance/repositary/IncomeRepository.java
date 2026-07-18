package com.example.finance.repositary;


import com.example.finance.model.IncomeModel;
import java.time.LocalDate;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface IncomeRepository extends JpaRepository<IncomeModel,Integer>{

    @Query("SELECT COALESCE(SUM(i.amount),0) FROM IncomeModel i")
    Double getTotalIncome();

    @Query("select u from IncomeModel as u where u.id=:id")
    public IncomeModel ShowAllDetails(@RequestParam("id") Long id);


    @Transactional
    @Modifying
    @Query("DELETE FROM IncomeModel u WHERE u.id=:id")
    void deleteById(@RequestParam("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE IncomeModel u set u.source=:source,u.amount=:amount,u.date=:date where u.id=:id")
    void UpdateRecord(
            @RequestParam("tid") Long id,
            @RequestParam("tso") String source,
            @RequestParam("tde") Double amount,
            @RequestParam("tda") LocalDate date);


    @Query("SELECT i FROM IncomeModel i WHERE i.date BETWEEN :startDate AND :endDate ORDER BY i.date")
    List<IncomeModel> getIncomeReport(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate);

}
