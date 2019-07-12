package cn.hisin.demo.callback;

/**
 * @author 胡海鑫
 */
public class Mom {

    /**
     * 妈妈开始做饭，做好饭叫儿子吃饭，
     *
     *
     */
    public void cooking(Callback callback,String message){
        System.out.println("儿子的问题:"+message);
        new Thread( () ->{
            System.out.println("………………正在做饭………………");
            try {
                Thread.sleep(3000L);
                callback.eat("可以了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
