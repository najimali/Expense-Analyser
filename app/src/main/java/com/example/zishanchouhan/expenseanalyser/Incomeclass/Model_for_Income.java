package com.example.zishanchouhan.expenseanalyser.Incomeclass;

/**
 * Created by krishna on 17/7/18.
 */

public class Model_for_Income {

    int id;
    String category,joiningdate;
    double amount;

    public Model_for_Income(int id, String category, String joiningdate, double amount) {
        this.id = id;
        this.category = category;
        this.joiningdate = joiningdate;
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

    public String getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(String joiningdate) {
        this.joiningdate = joiningdate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
