package com.example.a52weeksaving.models;

public class AccountDetails {
    private Integer deposit = 0;

    public AccountDetails(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getDeposit() {
//        todo fix the constraints here
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }
}
