package com.wilson.okhttp_analysis.design_pattern.chain_of_responsibility;

//主管
public  abstract class Handler {
    protected String name; // 处理者姓名
    protected Handler nextHandler;  // 下一个处理者

    public Handler(String name) {
        this.name = name;
    }

    public abstract boolean process(LeaveRequest leaveRequest); // 处理请假

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Handler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
