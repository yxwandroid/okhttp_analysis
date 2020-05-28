package com.wilson.okhttp_analysis.design_pattern.chain_of_responsibility;

public class LeaveRequest {
    private String name;    // 请假人姓名
    private int numOfDays;  // 请假天数


    public LeaveRequest(String name, int numOfDays) {
        this.name = name;
        this.numOfDays = numOfDays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }
}
