/*
 * Copyright (C) 2016-2020 IMassBank Corporation
 *
 */
package com.example.day1;

/**
 * s
 *
 * @author:lh
 */
public class Ste {

    private String name;
    private String month;
    private String invokeTotalAmount;
    private String bileTotalAmount;

    public Ste() {
    }

    public Ste(String name, String month, String invokeTotalAmount, String bileTotalAmount) {
        this.name = name;
        this.month = month;
        this.invokeTotalAmount = invokeTotalAmount;
        this.bileTotalAmount = bileTotalAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getInvokeTotalAmount() {
        return invokeTotalAmount;
    }

    public void setInvokeTotalAmount(String invokeTotalAmount) {
        this.invokeTotalAmount = invokeTotalAmount;
    }

    public String getBileTotalAmount() {
        return bileTotalAmount;
    }

    public void setBileTotalAmount(String bileTotalAmount) {


        this.bileTotalAmount = bileTotalAmount;
    }

    @Override
    public String toString() {
        return "Ste{" +
                "name='" + name + '\'' +
                ", month='" + month + '\'' +
                ", invokeTotalAmount='" + invokeTotalAmount + '\'' +
                ", bileTotalAmount='" + bileTotalAmount + '\'' +
                '}';
    }
}
