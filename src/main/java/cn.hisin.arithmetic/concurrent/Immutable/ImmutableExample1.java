package cn.hisin.arithmetic.concurrent.Immutable;

import com.sun.imageio.plugins.common.I18N;

import java.util.HashMap;
import java.util.Map;

/**
 * 不可变对象
 * final 修饰常量，值一旦定义，后期不可更改
 *               修饰对象，一旦定义，后期不可指向其他对象
 */
public class ImmutableExample1 {

    private final static Integer i =1;
    private final static String s="s";
    private final static Map<String,Object> hashMap= new HashMap<>(0);

}
