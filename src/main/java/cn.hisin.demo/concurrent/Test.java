package cn.hisin.demo.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author hisin
 * @date 2020/3/10 17:31
 */
public class Test {

    private String var;

    public static void main(String[] args) {
        Test test = new Test();
        test.setVar("A");
//        Thread A = new Thread(new A(test));
//        Thread B = new Thread(new B(test));
//        Thread C = new Thread(new C(test));
//        A.start();
//        B.start();
//        C.start();
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("print-msg-d")
                .build();
        List<Runnable> blockingQueue = new LinkedList<>();
        blockingQueue.add(new A(test));
        blockingQueue.add(new B(test));
        blockingQueue.add(new C(test));
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
                5, 1000L, TimeUnit.SECONDS, new SynchronousQueue<>(true), namedThreadFactory);

        try {
            for (Runnable runnable : blockingQueue) {
                threadPoolExecutor.execute(runnable);
            }
        }finally {
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
