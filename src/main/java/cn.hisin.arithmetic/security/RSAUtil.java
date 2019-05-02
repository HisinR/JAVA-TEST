package cn.hisin.arithmetic.security;


import org.apache.commons.codec.binary.Base64;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA加密工具
 * @author hisin
 */
public class RSAUtil {

    /**
     * 生成密钥对
     * @param initialize 生成密钥的初始化长度 可选:1024或者2048
     * @return 返回一个map，容器里有公钥和私钥
     */
    public static Map<String,Object> generateKeyPair(int initialize){
        try {
            KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
            rsa.initialize(initialize);
            KeyPair keyPair = rsa.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            Map<String,Object> keyMap = new HashMap<>(1 << 2);
            keyMap.put("publicKey",Base64.encodeBase64String(publicKey.getEncoded()));
            keyMap.put("privateKey",Base64.encodeBase64String(privateKey.getEncoded()));
            return keyMap;
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 传入一个参数返回这个参数的加密之后的串
     * @param bytes 加密数据
     * @param privateKey 私钥
     * @return 加密之后的串 ,返回null,签名失败
     */
    public static String  sign(byte[] bytes,String privateKey){
        try {
            byte[] key = Base64.decodeBase64(privateKey);
            //私钥使用PKCS8Encoded
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key);
            KeyFactory rsa = KeyFactory.getInstance("RSA");
            //生成私钥
            PrivateKey privateKey1 = rsa.generatePrivate(pkcs8EncodedKeySpec);
            //开始签名
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(privateKey1);
            signature.update(bytes);
            byte[] sign = signature.sign();
            return Base64.encodeBase64String(sign);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 验证签名是否正确
     * @return 返回true或false
     */
    public static boolean verify(byte[] data, String publicKey, String sign)  {
        try {
            byte[] pubKey =Base64.decodeBase64(publicKey);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pubKey);
            KeyFactory rsa = KeyFactory.getInstance("RSA");
            PublicKey generatePublic = rsa.generatePublic(x509EncodedKeySpec);
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(generatePublic);
            signature.update(data);
            return  signature.verify(Base64.decodeBase64(sign));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args)  {
        Map<String, Object> stringObjectMap = generateKeyPair(2048);
        Object privateKey = stringObjectMap.get("privateKey");
        Object publicKey = stringObjectMap.get("publicKey");
        System.out.println("privateKey="+privateKey);
        System.out.println("publicKey="+publicKey);
        String sign = sign("hhh".getBytes(StandardCharsets.UTF_8), privateKey.toString());
        System.out.println("sign="+sign);
        boolean verify = verify("哈哈哈版本的部分".getBytes(StandardCharsets.UTF_8), publicKey.toString(), sign);
        if (verify){
            System.out.println("验证成功");
        }else {
            System.out.println("验证失败");
        }
    }
}
