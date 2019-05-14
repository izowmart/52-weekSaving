package com.example.a52weeksaving.models;

public class Account {
    String week;
    String amount_to_save;
    String total_saved;

    public Account(String week, String amount_to_save, String total_saved) {
        this.week = week;
        this.amount_to_save = amount_to_save;
        this.total_saved = total_saved;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getAmount_to_save() {
        return amount_to_save;
    }

    public void setAmount_to_save(String amount_to_save) {
        this.amount_to_save = amount_to_save;
    }

    public String getTotal_saved() {
        return total_saved;
    }

    public void setTotal_saved(String total_saved) {
        this.total_saved = total_saved;
    }
}
