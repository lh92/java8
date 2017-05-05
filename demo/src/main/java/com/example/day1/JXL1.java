package com.example.day1;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class JXL1 implements Runnable {

    private String idNumber;

    private String phone;

    private String name;

    JXL1(String idNumber, String phone, String name) {
        this.idNumber = idNumber;
        this.phone = phone;
        this.name = name;
    }

    JXL1() {
    }


    private ExecutorService executor = Executors.newCachedThreadPool();

    public void registerTask(Runnable runnable) {
        addTask(runnable);
    }

    private void addTask(Runnable runnable) {
        executor.execute(runnable);
    }

    private <V> V addTask(Callable<V> callable) {
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

    private void close() {
        executor.shutdown();
    }

    @Override
    public void run() {

        log.info("this is---JXL1--{}---{}---{}", this.idNumber, this.phone, this.name);
        JXL1 jxl1 = new JXL1(this.idNumber, this.phone, this.name);

        //jxl1.addTask(new jxl2(this.idNumber, this.phone, this.name));
        jxl1.addTask(new jxl4());
        jxl1.addTask(new jxl3());
        jxl1.close();
    }

    public static void main(String[] args) {
        final TestNumber testNumber = new TestNumber();
        JXL1 jxl1 = new JXL1();
        jxl1.addTask(new jxl2(testNumber));
        jxl1.addTask(new jxl4(testNumber));
        jxl1.addTask(new jxl3(testNumber));
        jxl1.close();
    }

    static class jxl2 extends Vo {

//        jxl2(String idNumber, String phone, String name) {
//            this.idNumber = idNumber;
//            this.phone = phone;
//            this.name = name;
//        }

        TestNumber testNumber;

        jxl2(TestNumber testNumber) {
            this.testNumber = testNumber;
        }

        @Override
        public void run() {
            System.out.println("jxl2");
            for (int i = 0; i <= 10; i++) {

                try {
                    Thread.sleep(200);
                    testNumber.incrment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            log.info("this is---jxl2");
            log.info("this is---jxl2--{}---{}---{}", this.idNumber, this.phone, this.name);
        }
    }

    static class jxl3 implements Runnable {

        TestNumber testNumber;

        jxl3(TestNumber testNumber) {
            this.testNumber = testNumber;
        }

        jxl3() {
        }

        @Override
        public void run() {
            System.out.println("jxl3");
            for (int i = 0; i <= 10; i++) {

                try {
                    Thread.sleep(300);
                    testNumber.decrment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            log.info("this is---jxl3");
        }
    }


    static class jxl4 implements Runnable {

        TestNumber testNumber;

        jxl4(TestNumber testNumber) {
            this.testNumber = testNumber;
        }

        jxl4() {
        }

        @Override
        public void run() {
            System.out.println("jxl4");

            log.info("this is---jxl4");
            //throw new NullPointerException();
        }
    }

}

class TestNumber {

    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void incrment() throws Exception {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();//this.wait();
            }
            ++number;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();//this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrment() throws Exception {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();//this.wait();
            }
            --number;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();//this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
