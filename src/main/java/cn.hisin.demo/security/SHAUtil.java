package cn.hisin.demo.security;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHAUtil工具类
 */
public class SHAUtil {
    /**
     * 获取SHA**类型的哈希散列值
     *
     * @param str  要加密的字符，
     * @param type 默认使用SHA-256,可指定
     * @return 返回一个密码串
     */
    public static String getSHA(String str, String type) {
        try {
            MessageDigest sha;
            if (type == null) {
                sha = MessageDigest.getInstance("SHA-256");
                sha.update(str.getBytes(StandardCharsets.UTF_8));
                return toHexString(sha.digest());
            }
            sha = MessageDigest.getInstance(type);
            sha.update(str.getBytes(StandardCharsets.UTF_8));
            return toHexString(sha.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转化为16进制
     *
     * @param hex
     * @return
     */
    private static String toHexString(byte[] hex) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hex) {
            //byte转int时会进行补位，需要将高位变0
            int k = (int) b & 0xff;
            if (k == 1) {
                stringBuilder.append(0);
            }
            stringBuilder.append(Integer.toHexString(k));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String sha256 = getSHA("哈哈", "SHA-256");
        System.out.println(sha256);
//        Provider[] providers = Security.getProviders();
//        for (Provider provider : providers) {
//            System.out.println(provider.getName() + ":\t" + provider.getInfo());
//        }
    }
}
