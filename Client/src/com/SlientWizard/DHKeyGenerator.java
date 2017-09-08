package com.SlientWizard;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class DHKeyGenerator {
    private Map<String, Object> keyPair;
    private static final String KEY_ALGORITHM = "DH";
    private static final String SELECT_ALGORITHM = "AES";
    private static final int KEY_SIZE = 512;
    private static final String PUBLIC_KEY = "DHPublicKey";
    private static final String PRIVATE_KEY = "DHPrivataeKey";
    public DHKeyGenerator() throws Exception
    {
        initKey();
    }
    public DHKeyGenerator(byte[] key) throws Exception
    {
        initKey(key);
    }
    public void initKey() throws Exception
    {
        KeyPairGenerator KeyPairGenerator = java.security.KeyPairGenerator.getInstance(KEY_ALGORITHM);
        KeyPairGenerator.initialize(KEY_SIZE);
        KeyPair KeyPair = KeyPairGenerator.generateKeyPair();
        DHPublicKey publicKey = (DHPublicKey)KeyPair.getPublic();
        DHPrivateKey privateKey = (DHPrivateKey)KeyPair.getPrivate();
        keyPair = new HashMap(2);
        keyPair.put(PUBLIC_KEY, publicKey);
        keyPair.put(PRIVATE_KEY, privateKey);
    }
    public void initKey(byte[] key) throws Exception
    {
        X509EncodedKeySpec X509KeySpec = new X509EncodedKeySpec(key);
        java.security.KeyFactory keyFactory = java.security.KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey pubKey = keyFactory.generatePublic(X509KeySpec);
        DHParameterSpec dhParameterSpec = ((DHPublicKey)pubKey).getParams();
        KeyPairGenerator KeyPairGenerator = java.security.KeyPairGenerator.getInstance(KEY_ALGORITHM);
        KeyPairGenerator.initialize(KEY_SIZE);
        KeyPair KeyPair = KeyPairGenerator.generateKeyPair();
        DHPublicKey publicKey = (DHPublicKey)KeyPair.getPublic();
        DHPrivateKey privateKey = (DHPrivateKey)KeyPair.getPrivate();
        keyPair = new HashMap(2);
        keyPair.put(PUBLIC_KEY, publicKey);
        keyPair.put(PRIVATE_KEY, privateKey);
    }

    public byte[] getSecretKey(byte[] publicKey, byte[] privateKey) throws Exception
    {
        java.security.KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        KeyAgreement keyAgree = KeyAgreement.getInstance(keyFactory.getAlgorithm());
        keyAgree.init(priKey);
        keyAgree.doPhase(pubKey, true);
        SecretKey secretKey = keyAgree.generateSecret(SELECT_ALGORITHM);
        return secretKey.getEncoded();
    }
    public byte[] getPublicKey() throws Exception
    {
        Key key = (Key)keyPair.get(PUBLIC_KEY);
        return key.getEncoded();
    }
    public byte[] getPrivateKey() throws Exception
    {
        Key key = (Key)keyPair.get(PRIVATE_KEY);
        return key.getEncoded();
    }
}
