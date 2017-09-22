package com.SlientWizard;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import java.security.KeyFactory;
import javax.crypto.KeyGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

//Generate symmetric key
//There are two constructed functions
//The one with parameter is used for generate key with DH key
//The other do not have parameter and you can use it generate a random key
public class SymmetricKeyGenerator {

    private String KEY_ALGORITHM = "AES";
    private int KEY_SIZE = 512;
    private SecretKey secretKey;

    //Client use its private key and server's public key to generate symmetric secret key.
    // Server use its private key and client's public key to generate symmetric secret key.
    public SymmetricKeyGenerator(DHPublicKey pubKey, DHPrivateKey priKey) throws Exception
    {
        byte[] publicKey = pubKey.getEncoded();
        byte[] privateKey = priKey.getEncoded();
        java.security.KeyFactory keyFactory = KeyFactory.getInstance("DH");
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
        PublicKey pubKey1 = keyFactory.generatePublic(x509KeySpec);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
        PrivateKey priKey1 = keyFactory.generatePrivate(pkcs8KeySpec);
        KeyAgreement keyAgree = KeyAgreement.getInstance(keyFactory.getAlgorithm());
        keyAgree.init(priKey1);
        keyAgree.doPhase(pubKey1, true);
        secretKey = keyAgree.generateSecret(KEY_ALGORITHM);
    }

    //Generate symmetric key without parameter
    public SymmetricKeyGenerator() throws Exception
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(KEY_SIZE);
        secretKey = keyGenerator.generateKey( );
    }

    public SecretKey getSecretKey () throws Exception
    {
        return secretKey;
    }
}
