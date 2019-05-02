package cn.hisin.arithmetic.concurrent;


/**
 *
 * 模拟死锁
 */
public class Deadlock {

    private static final String A="A";
    private static final String B="B";

    public static void main(String[] args) {
        deadLock();
    }

    /**
     * 避免一个线程同时获得多个锁
     *
     * 避免一个线程在锁内占用多个资源，尽量保证一个锁占用一个资源
     * 尝试使用定时锁，使用lock.tryLock（timeout）来替代使用内部锁机制。
     * 对于数据库锁，加锁和解锁必须在一个数据库连接里，否则会出现解锁失败的情况。
     */
    public static void deadLock(){
        Thread t1=new Thread(()->{
            synchronized (A){
                try {
                    Thread.currentThread().sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B){
                    System.out.println("1");
                }
            }
        });

        Thread t2 = new Thread(()->{
           synchronized (B){
               synchronized (A){
                   System.out.println(2);
               }
           }
        });
        t1.start();
        t2.start();
    }
}
