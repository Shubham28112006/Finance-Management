package com.example.finance.repositary;

import com.example.finance.model.IncomeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<IncomeModel,Integer>{

    @Query("SELECT COALESCE(SUM(i.amount),0) FROM IncomeModel i")
    Double getTotalIncome();

}
