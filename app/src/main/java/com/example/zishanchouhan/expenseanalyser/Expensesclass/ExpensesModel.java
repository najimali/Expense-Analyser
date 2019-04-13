package com.example.zishanchouhan.expenseanalyser.Expensesclass;

/**
 * Created by Zishan Chouhan on 16-Jul-18.
 */

public class ExpensesModel {

    int id;
    String category,joiningDate;
    double amount;

    public ExpensesModel(int id, String category, String joiningDate, double amount) {

        this.id = id;
        this.category = category;
        this.joiningDate = joiningDate;
        this.amount = amount;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
