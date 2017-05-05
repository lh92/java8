package com.example.day1;

import lombok.Data;


@Data
public abstract class Vo implements Runnable {

    String idNumber;

    String phone;

    String name;
}
