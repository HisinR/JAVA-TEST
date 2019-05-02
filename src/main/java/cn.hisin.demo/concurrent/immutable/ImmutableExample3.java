package cn.hisin.arithmetic.concurrent.immutable;

import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.Map;


/**
 * @author hisin
 * 使用guava 生成不可变对象
 */
public class ImmutableExample3 {

    private static ImmutableList<Object> buildList = ImmutableList.builder().add("a").add("b").add("c").build();

    private static ImmutableList<String> list = ImmutableList.of("1","2","3");
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(1 << 8);
        map.put("a",1);
        System.out.println("hiikdjfiangkojnjjs".hashCode() & 15);
    }
}
