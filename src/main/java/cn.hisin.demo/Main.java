package cn.hisin.demo;

/**
 * @author 胡海鑫
 * @date 2019/3/6 13:09
 */
public class Main {
    public static void main(String[] args) {
    fun3(()->{
        System.out.println("dsfas");
    });

    }

    public static void fun2(LambdaTest lambdaTest) {
            lambdaTest.fun();
    }
    public static void fun3(LambdaTest2 lambdaTest) {
        lambdaTest.fun();
    }
}
