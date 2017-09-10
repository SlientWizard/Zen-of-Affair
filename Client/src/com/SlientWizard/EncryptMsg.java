package com.SlientWizard;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;

public class EncryptMsg implements Serializable{

    private static final String SELECT_ALGORITHM = "AES";
    //String encrypt(String ciphertext,String key){return null;}
    //String decrypt(String plaintext, String key){return null;}

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception
    {
        SecretKey secretKey = new SecretKeySpec(key, SELECT_ALGORITHM);
        Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }
   
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception
    {
        SecretKey secretKey = new SecretKeySpec(key, SELECT_ALGORITHM);
        Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }
}
