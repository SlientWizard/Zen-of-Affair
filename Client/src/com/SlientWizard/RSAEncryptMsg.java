package com.SlientWizard;

import javax.crypto.Cipher;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

//Usage: use RSA public key or private key to encrypt or decrypt
//There are two encrypt() and decrypt() methods for public key and private key
//You can get key from class RSAKeyGenerator by using getPublicKey() and getPrivateKey() method
public class RSAEncryptMsg {

    public static byte[] encrypt(byte[] data, RSAPublicKey publicKey)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    public static byte[] encrypt(byte[] data, RSAPrivateKey privateKey)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, RSAPrivateKey privateKey)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(String data, RSAPublicKey publicKey)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data.getBytes());
    }
}
