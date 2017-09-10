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
public class DHKeyGenerator {
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

    //Client use its private key and server's public key to generate symmetric secret key.
    // Server use its private key and client's public key to generate symmetric secret key.
    public byte[] getSecretKey(DHPublicKey pubKey, DHPrivateKey priKey) throws Exception
    {
        byte[] publicKey = pubKey.getEncoded();
        byte[] privateKey = priKey.getEncoded();
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
        PublicKey pubKey1 = keyFactory.generatePublic(x509KeySpec);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
        PrivateKey priKey1 = keyFactory.generatePrivate(pkcs8KeySpec);
        KeyAgreement keyAgree = KeyAgreement.getInstance(keyFactory.getAlgorithm());
        keyAgree.init(priKey1);
        keyAgree.doPhase(pubKey1, true);
        SecretKey secretKey = keyAgree.generateSecret(SELECT_ALGORITHM);
        return secretKey.getEncoded();
    }

    public DHPublicKey getPublicKey() throws Exception
    {
        DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();
        return publicKey;
    }

    public DHPrivateKey getPrivateKey() throws Exception
    {
        DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
        return privateKey;
    }
}
