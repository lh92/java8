package com.example.day1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class TestPool {

    private ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * 注册线程
     */
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

    /**
     * 关闭线程池
     */
    private void close() {
        executor.shutdown();
    }

    public static void main(String[] args) {

        final Ticket ticket = new Ticket();

        TestPool main = new TestPool();
        main.registerTask(new rrxHelp(ticket));
        main.registerTask(new jxlHelp(ticket));
        main.registerTask(new tlHelp(ticket));
        main.registerTask(new JXL1("1", "2", "3"));
        main.close();
    }

    static class rrxHelp implements Runnable {

        private Ticket ticket;

        rrxHelp(Ticket ticket) {
            this.ticket = ticket;
        }

        @Override
        public void run() {
            log.info("this is---rrxHelp");

            for (int i = 0; i < 40; i++) {
                System.out.println("rrxHelp" + i);
                ticket.sale();
            }
        }
    }

    static class jxlHelp implements Runnable {

        private Ticket ticket;

        jxlHelp(Ticket ticket) {
            this.ticket = ticket;
        }

        @Override
        public void run() {

            log.info("this is---jxlHelp");

            for (int i = 0; i < 40; i++) {
                System.out.println("jxlHelp" + i);
                ticket.sale();
            }
        }
    }

    static class tlHelp implements Runnable {

        private Ticket ticket;

        tlHelp(Ticket ticket) {
            this.ticket = ticket;
        }

        @Override
        public void run() {

            log.info("this is---tlHelp");

            for (int i = 0; i < 40; i++) {
                System.out.println("tlHelp" + i);
                ticket.sale();
            }
        }
    }

}

class Ticket {

    private int number = 30;
    private int a = 1;
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();

        try {

            if (number > 0) {
                Thread.sleep(200);
                System.out.println("卖出第" + (a++) + "张票,还剩余" + (--number) + "张票");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
