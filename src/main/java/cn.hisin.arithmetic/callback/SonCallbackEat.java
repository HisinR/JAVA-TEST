package cn.hisin.arithmetic.callback;


/**
 * @author hisin
 */
public class SonCallbackEat implements Callback {

    private Mom mom;

   public SonCallbackEat(Mom mom){
       this.mom=mom;
   }
    @Override
    public void eat(String message) {
        System.out.println("妈妈的回答;"+message);
    }

    public void askMom(String message) throws InterruptedException {
        new Thread(()->{
           mom.cooking(SonCallbackEat.this,message);
        }).start();
        Thread.sleep(2000L);
        System.out.println("我出去玩会好了叫我");
    }



}
