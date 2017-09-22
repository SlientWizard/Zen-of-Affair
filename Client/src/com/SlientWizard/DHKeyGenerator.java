package com.SlientWizard;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

//Usage: generate public key and private key for DH key exchange agreement and symmetric secret key.
//Client use generateKey() method to generate key pair
//Server use generateKey(client's public key) method to generate key pair
public class DHKeyGenerator implements KeyGenerator{
    private KeyPair keyPair;
    private static final String KEY_ALGORITHM = "DH";
    private static final String SELECT_ALGORITHM = "AES";
    private static final int KEY_SIZE = 512;
    public void generateKey() throws Exception
    {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        keyPair = keyPairGenerator.generateKeyPair();
    }

    public void generateKey(DHPublicKey publicKey) throws Exception
    {
        byte[] key = publicKey.getEncoded();
        X509EncodedKeySpec X509KeySpec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey pubKey = keyFactory.generatePublic(X509KeySpec);
        DHParameterSpec dhParameterSpec = ((DHPublicKey)pubKey).getParams();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        keyPair = keyPairGenerator.generateKeyPair();
    }

    public PublicKey getPublicKey()// throws Exception
    {
        return keyPair.getPublic();
    }

    public PrivateKey getPrivateKey() //throws Exception
    {
        return keyPair.getPrivate();
    }
}
