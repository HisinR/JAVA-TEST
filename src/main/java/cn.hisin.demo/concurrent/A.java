package cn.hisin.demo.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author hisin
 * @date 2020/3/10 17:28
 */
public class A {

    private final Test test;

    private final Lock lock;

    private final Condition condition;


    public A(Test test, Lock lock, Condition condition) {
        this.test = test;
        this.lock = lock;
        this.condition = condition;
    }

    public void run() {
        lock.lock();
        try {
            if (test.getVar().equals("A")) {
                System.out.print("A");
                test.setVar("B");
                condition.signalAll();
            }
            condition.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
