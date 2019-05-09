package com.example.a52weeksaving.models;

public class AccountDetails {
    private Integer [] week_number;
    private Integer deposit;
    private Integer total_amount;
    private Integer total_saving;

    public AccountDetails(Integer deposit, Integer total_amount) {
        this.deposit = deposit;
        this.total_amount = total_amount;

//        todo fix the total saving
        this.total_saving = null;
    }

    public int getWeek_number() {
        Integer [] weeks_number = {1,2,3,4,5,6,7,8,9,10,11};
        for(int i=0 ; i<weeks_number.length; i++){
            return i;
        }

        return 0;
    }

    public void setWeek_number(Integer[] week_number) {
        this.week_number = week_number;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Integer total_amount) {
        this.total_amount = total_amount;
    }

    public Integer getTotal_saving() {
        return total_saving;
    }

    public void setTotal_saving(Integer total_saving) {
        this.total_saving = total_saving;
    }
}
