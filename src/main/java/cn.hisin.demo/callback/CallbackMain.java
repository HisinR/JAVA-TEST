package cn.hisin.arithmetic.callback;

public class CallbackMain {

    public static void main(String[] args) throws InterruptedException {
        SonCallbackEat sonCallbackEat = new SonCallbackEat(new Mom());
        sonCallbackEat.askMom("饭好了没");
    }
}
