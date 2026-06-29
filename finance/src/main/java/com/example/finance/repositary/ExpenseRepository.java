package com.example.finance.repositary;

import com.example.finance.model.ExpenseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseModel,Integer>{

    @Query("SELECT COALESCE(SUM(e.amount),0) FROM ExpenseModel e")
    Double getTotalExpense();

}