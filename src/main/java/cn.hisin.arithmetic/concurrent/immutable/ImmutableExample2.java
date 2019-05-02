package cn.hisin.arithmetic.concurrent.immutable;

import java.util.*;

/**
 * @author hisin
 *
 * 使用Collections把可变对象变成不可变对象
 *
 *
 */
public class ImmutableExample2 {

    private static List<String> list = new ArrayList<>();
    private static Set<String> set = new HashSet<>();

    static {
        list.add("a");
        list.add("b");
        list.add("c");
        list = Collections.unmodifiableList(list);
    }

    public static void main(String[] args) {
        /*
         变成不可修改得对象，调用添加方法将抛出  UnsupportedOperationException
        */
        list.add("d");
    }

}
