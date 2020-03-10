package cn.hisin.demo.concurrent;

/**
 * @author hisin
 * @date 2020/3/10 17:28
 */
public class A implements Runnable {

    private final Test test;

    public A(Test test) {
        this.test = test;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i <= 10; i++) {
                synchronized (test) {
                    if (test.getVar().equals("A")) {
                        System.out.print("A");
                        test.setVar("B");
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
