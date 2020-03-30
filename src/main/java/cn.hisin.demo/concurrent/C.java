package cn.hisin.demo.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author hisin
 * @date 2020/3/10 17:30
 */
public class C implements Runnable {


    private final Test test;
    private final Lock lock;
    private final Condition condition;

    public C(Test test, Lock lock, Condition condition) {
        this.test = test;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                if (test.getVar().equals("C")) {
                    System.out.println("C");
                    test.setVar("A");
                    condition.signalAll();
                    lock.unlock();
                }
                condition.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
