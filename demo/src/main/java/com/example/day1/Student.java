package com.example.day1;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 学生类
 *
 * @author:lh
 */
public class Student {
    private String name;
    private int score;
    private int age;


    public Student(String name, int score, int age) {
        this.name = name;
        this.score = score;
        this.age = age;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public static void main(String[] args) {
        // 长度为3的线程数组
        Thread threads[] = new Thread[3];

        // 创建线程并且运行任务
        for (int i = 0; i < threads.length; i++) {
            TaskLocalRandom task = new TaskLocalRandom();
            threads[i] = new Thread(task);
            threads[i].start();
        }
    }
}

class TaskLocalRandom implements Runnable {

    /**
     * 构造函数，初始化当前类的随机数生成对象
     */
    public TaskLocalRandom() {
        ThreadLocalRandom.current();
    }

    /**
     * 核心方法，生成一个[0, 10)的随机数
     */
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: %d\n", name, ThreadLocalRandom.current().nextInt(10));
        }
    }
}
