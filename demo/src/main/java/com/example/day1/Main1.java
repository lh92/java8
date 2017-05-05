package com.example.day1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main1 {
    public static int Surplus = 10;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    void addTask(Runnable runnable) {
        executor.execute(runnable);
    }

    <V> V addTask(Callable<V> callable) {
        Future<V> submit = executor.submit(callable);
        try {
            return submit.get();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException" + e.toString());
        } catch (ExecutionException e) {
            System.out.println("ExecutionException" + e.toString());
        }
        return null;
    }

    public void testAddTask(String name) {
        addTask(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    if (Main1.Surplus <= 0)
                        return;
                    Main1.Surplus--;
                    System.out.println(name + "  " + Main1.Surplus);
                }

            }
        });
    }

    public void testAddTask2(String name) {
        int count = addTask(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                for (int i = 0; i < 3; i++) {
                    if (Main1.Surplus <= 0)
                        return 0;
                    Main1.Surplus--;
                    System.out.println(name + "  " + Main1.Surplus);
                }
                return Main1.Surplus;
            }
        });

    }

    public void close() {
        executor.shutdown();
    }

    public static void main(String[] args) {
        Main1 main = new Main1();
        main.testAddTask("task1");
        main.testAddTask2("task2");
        main.testAddTask("task3");
        main.testAddTask2("task4");
        main.close();
    }
}