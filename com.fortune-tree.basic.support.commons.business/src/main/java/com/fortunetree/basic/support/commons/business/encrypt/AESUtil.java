package com.fortunetree.basic.support.commons.business.encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @ClassName AESUtil
 *
 * 默认配置
 * 加密类型/模式/填充 AES/CFB/NoPadding
 * key 长度128
 * @Description TODO
 * @Author jb.zhou
 * @Date 2019/9/24
 * @Version 1.0
 */
public final class AESUtil {
    private final static Logger log = LoggerFactory.getLogger(AESUtil.class);

    private static final String CHAR_SET_UTF8 = "utf-8";
    private static final String SHA1PRNG = "SHA1PRNG";
    private static final Integer KEY_LENGTH = 128;
    private static final String defEncryptKey = "jumboo1325";
    private static final String KEY_ALGORITHM = "AES";
    static final String  ALGORITHM_CBC = "AES/CBC/PKCS5Padding";
    static final String  ALGORITHM_CFB = "AES/CFB/PKCS5Padding";
    static final String  ALGORITHM_CFB_NOPADDING = "AES/CFB/NoPadding";
    static final String IV = "1003311003311003";

    /**
     * 生成偏移量
     * @return
     */
    static byte[] getIV() {
        return IV.getBytes();
    }
    static byte[] getIV(String iv) {
        if(isEmpty(iv) || iv.length()!=16){
            throw new CipherAesException("偏移量不符合要求，必须16字节");
        }
        return IV.getBytes();
    }

    /**
     * 生成SecretKey
     * @param keySeed
     * @return
     */
    static SecretKey getKey(String keySeed) {
        if (isEmpty(keySeed)) {
            keySeed = defEncryptKey;
        }
        try {
//            KeyGenerator generator = KeyGenerator.getInstance(KEY_ALGORITHM);
//            SecureRandom secureRandom = SecureRandom.getInstance(SHA1PRNG);
//            secureRandom.setSeed(keySeed.getBytes());
//            generator.init(KEY_LENGTH, secureRandom);
//            SecretKey secretKey = generator.generateKey();
//            byte[] raw = secretKey.getEncoded();
//            System.out.println("key length:"+raw.length);
//            SecretKey key = new SecretKeySpec(raw, KEY_ALGORITHM);
//            return key;
            byte[] keyBytes = Arrays.copyOf(keySeed.getBytes("ASCII"), 16);
//            System.out.println("key length:"+keyBytes.length);
            SecretKey key = new SecretKeySpec(keyBytes, "AES");
            return key;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 加密
     * 不建议使用
     * AES 默认key  默认algorithm  默认iv
     */
    public static String encode(String content) throws Exception {
        return encode(content, defEncryptKey,KEY_ALGORITHM, (String)null);
    }

    /**
     * @param content 待加密内容
     * @param encryptKey 加密key
     * @return String
     * @throws Exception
     */
    public static String encode(String content, String encryptKey) throws Exception {
        return encode(content, encryptKey,KEY_ALGORITHM, (String)null);
    }

    /**
     * @param content
     * @param encryptKey
     * @param iv
     * @return String
     * @throws Exception
     */
    public static String encode(String content, String encryptKey, String iv) throws Exception {
        return encode(content, encryptKey,KEY_ALGORITHM, (String)null);
    }

    public static String encode(String content, String encryptKey, String algorithm, String iv) throws Exception {
        if(isEmpty(content) || isEmpty(encryptKey) || isEmpty(algorithm)){
            return null;
        }
        try {
            SecretKey key = getKey(encryptKey);
            Cipher cipher = Cipher.getInstance(algorithm);
            try{
                if(isNotEmpty(iv)){
                    cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(getIV(iv)));
                }else{
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                }
            }catch (Exception e){
                throw new CipherAesException("CipherAesException：加密：加密器初始化错误:{}",e);
            }
            byte[] byte_encode = content.getBytes(CHAR_SET_UTF8);
            byte[] byte_AES = cipher.doFinal(byte_encode);
            String AES_encode = new String(base64Encode(byte_AES));
            return AES_encode;
        } catch (NoSuchAlgorithmException e) {
            log.error("AESUtil:NoSuchAlgorithmException:{}",e.getMessage());
        } catch (NoSuchPaddingException e) {
            log.error("AESUtil:NoSuchPaddingException:{}",e.getMessage());
        } catch (IllegalBlockSizeException e) {
            log.error("AESUtil:IllegalBlockSizeException:{}",e.getMessage());
        } catch (BadPaddingException e) {
            log.error("AESUtil:BadPaddingException:{}",e.getMessage());
        } catch (UnsupportedEncodingException e) {
            log.error("AESUtil:UnsupportedEncodingException:{}",e.getMessage());
        }
        return null;
    }



    /**
     * 解密
     * 不建议使用
     * AES 默认key  默认algorithm  默认iv
     */
    public static String decode(String content){
        return decode(content, defEncryptKey,KEY_ALGORITHM, (String)null);
    }

    public static String decode(String content, String encryptKey){
        return decode(content, encryptKey,KEY_ALGORITHM, (String)null);
    }

    public static String decode(String content, String encryptKey, String iv){
        return decode(content, encryptKey,KEY_ALGORITHM, (String)null);
    }

    public static String decode(String content, String encryptKey, String algorithm, String iv) {
        if(isEmpty(content) || isEmpty(encryptKey) || isEmpty(algorithm)){
            return null;
        }
        try {
            SecretKey key = getKey(encryptKey);
            Cipher cipher = Cipher.getInstance(algorithm);
            try{
                if(isNotEmpty(iv)){
                    cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(getIV(iv)));
                }else{
                    cipher.init(Cipher.DECRYPT_MODE, key);
                }
            }catch (Exception e){
                throw new CipherAesException("CipherAesException：解密：加密器初始化错误:{}",e);
            }
            byte[] byte_content = base64Decode(content);
            byte[] byte_decode = cipher.doFinal(byte_content);
            String AES_decode = new String(byte_decode, CHAR_SET_UTF8);
            return AES_decode;
        } catch (NoSuchAlgorithmException e) {
             log.error("AESUtil:NoSuchAlgorithmException :{}",e.getMessage());
        } catch (NoSuchPaddingException e) {
             log.error("AESUtil:NoSuchPaddingException :{}",e.getMessage());
        } catch (InvalidKeyException e) {
             log.error("AESUtil:InvalidKeyException :{}",e.getMessage());
        } catch (IOException e) {
             log.error("AESUtil:IOException :{}",e.getMessage());
        } catch (IllegalBlockSizeException e) {
             log.error("AESUtil:IllegalBlockSizeException :{}",e.getMessage());
        } catch (BadPaddingException e) {
             log.error("AESUtil:BadPaddingException :{}",e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            log.error("AESUtil:InvalidAlgorithmParameterException :{}",e.getMessage());
        } catch (CipherAesException e) {
            log.error("AESUtil:CipherAesException :{}",e.getErrorMessage());
        } catch (Exception e) {
            log.error("AESUtil:base64Decode :{}",e.getMessage());
        }
        return null;
    }

    /**
     * base 64 加密
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes){
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * base 64 解密
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception{
        return isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }


    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        if(isEmpty(content) || isEmpty(encryptKey)){
            return null;
        }
        KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
        kgen.init(KEY_LENGTH, new SecureRandom(encryptKey.getBytes()));
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), KEY_ALGORITHM));

        return cipher.doFinal(content.getBytes(CHAR_SET_UTF8));
    }
    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
        kgen.init(128, new SecureRandom(decryptKey.getBytes()));

        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), KEY_ALGORITHM));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }



    /**
     * 将base 64 code AES解密
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    /**
     * AES加密为base 64 code
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }



    /**
     * From java lang3
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * From java lang3
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }


    public static void main(String[] args) throws Exception {

        String[] keys = {
                "这是一个测hi是啊1234324i！@#￥.%*()&$#=+-?<,/_&"
                ,"root"
                ,"qwertyuiopasdfg"
                ,"这是一个测试，呵呵。"
                ,"222222222222222"
                ,"22222222222222X"
                ,"111111111111111111"
                ,"11111111111111111X"
                ,"18311119999"
                ,"ksjdhflksadjfkajdkjfalkdhflakjdflakjdfhlakdhflkajdhflakdjflakdhfakjdfhlajdhflakjdhfalkjdfhaljkdhflaoiuvadf=-sdf"
        };
        for (String key : keys) {
            System.out.print("key:"+key + " | ");
            String encryptString = AESUtil.encode(key,"abc123");
            System.out.print(encryptString + " | ");
            String decryptString =  AESUtil.decode(encryptString,"abc123");
            System.out.println(decryptString);
        }
    }




}
