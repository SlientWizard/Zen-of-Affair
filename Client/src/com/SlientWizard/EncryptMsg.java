package com.SlientWizard;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.security.Key;
import java.util.HashMap;

public class EncryptMsg
{
    // Map for find the corresponding argument for cipher according to the input algorithm
    private static HashMap<String,String> map = new HashMap<String, String>()
    {
        {
            map.put("AES","AES/CBC/PKCS5Padding");
            map.put("RSA","RSA/ECB/PKCS1Padding");
        }
    };
    private String algorithm;
    public EncryptMsg(String algorithm)
    {
        this.algorithm = map.get(algorithm);
    }
    public byte[] encrypt(byte[] data,Key key) throws Exception
    {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    public byte[] decrypt(byte[] data, Key key) throws Exception
    {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }
}
