package com.wilson.okhttp_analysis.design_pattern.builder;
// 建造者指挥者
public class Director {
    private Builder builder ;
    // 使用多态，装机工非常多，我管你小美，小兰，小猪，我统统收了
    public Director(Builder builder){
        this.builder = builder ;
    }
    // 老板最后只想看到装成的成品---要交到客户手中
    public Computer createComputer(String cpu,String hardDisk,String mainBoard,String memory){
        // 具体的工作是装机工去做
        this.builder.createMainBoard(mainBoard);
        this.builder.createCpu(cpu) ;
        this.builder.createMemory(memory);
        this.builder.createhardDisk(hardDisk);
        return this.builder.createComputer() ;
    }

}

