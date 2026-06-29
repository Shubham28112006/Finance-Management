package com.example.finance.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ExpenseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double amount;
    @Column
    private String description;
    @Column
    private LocalDate date;

    public ExpenseModel() {
    }

    public Long getId() {
        return id;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}