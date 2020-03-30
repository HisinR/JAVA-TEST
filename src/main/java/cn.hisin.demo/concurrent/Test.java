package cn.hisin.demo.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hisin
 * @date 2020/3/10 17:31
 */
public class Test {

    private String var;

    public static void main(String[] args) {
        Test test = new Test();
        test.setVar("A");
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        A a = new A(test, lock, condition);
        B b = new B(test, lock, condition);
        C c = new C(test, lock, condition);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("print-msg-d")
                .build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
                5, 1000L, TimeUnit.SECONDS, new SynchronousQueue<>(true), namedThreadFactory);
        try {
            List<Runnable> runnableList = new LinkedList<>();
            runnableList.add(a);
            runnableList.add(b);
            runnableList.add(c);
            threadPoolExecutor.setKeepAliveTime(5,TimeUnit.SECONDS);

            for (Runnable runnable : runnableList) {
                threadPoolExecutor.execute(runnable);
            }
            int activeCount = threadPoolExecutor.getActiveCount();
            System.out.println(activeCount);
        } finally {
           threadPoolExecutor.shutdown();
        }
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }
}
