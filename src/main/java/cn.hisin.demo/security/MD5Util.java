package cn.hisin.arithmetic.security;


import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author hisin
 */
public class MD5Util {

    /**
     * 获取MD5加密之后字符串
     *
     * @param str 要加密的字符串
     * @return 返回一个被加密的字符串
     */
    public static String getMd5(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(str.getBytes());
//        StringBuilder stringBuilder = new StringBuilder();
//        for (byte b : digest) {
//            int var = b & 0xff;
//            if (var < 16) {
//                stringBuilder.append(0);
//            }
//            stringBuilder.append(Integer.toHexString(var));
//        }
        return String.valueOf(Hex.encodeHex(digest,false));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String md5 = MD5Util.getMd5("123456");
        System.out.println(md5);
        System.out.println(Integer.valueOf(0xff));
    }
}
