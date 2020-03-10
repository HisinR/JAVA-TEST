package cn.hisin.demo.concurrent;

/**
 * @author hisin
 * @date 2020/3/10 17:30
 */
public class C implements Runnable {


    private final Test test;

    public C(Test test) {
        this.test = test;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i <= 10; i++) {
                synchronized (test) {
                    if (test.getVar().equals("C")) {
                        System.out.println("C");
                        test.setVar("A");
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
