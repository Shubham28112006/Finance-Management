package com.example.finance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


import java.time.LocalDate;

@Entity
public class IncomeModel {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String source;

    @Column
     Double amount;

    @Column
     LocalDate date;

    public IncomeModel() {

    }

    public IncomeModel(String source, Double amount, LocalDate date) {
        this.source = source;
        this.amount = amount;
        this.date = date;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
