package cn.hisin.demo.concurrent;

/**
 * @author hisin
 * @date 2020/3/10 17:30
 */
public class B implements Runnable {

    private final Test test;

    public B(Test test) {
        this.test = test;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i <= 10; i++) {
                synchronized (test) {
                    if (test.getVar().equals("B")) {
                        System.out.print("B");
                        test.setVar("C");
                        test.notifyAll();
                    }
                    test.wait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
