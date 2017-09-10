package com.SlientWizard;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSAKeyGenerator {

    public KeyPair keyPair;
    public static final String KEY_ALGORITHM = "RSA";

    public RSAPublicKey getPublicKey() throws Exception
    {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        return publicKey;
    }

    public RSAPrivateKey getPrivateKey() throws Exception
    {
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return privateKey;
    }

    public void initKey() throws Exception
    {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        keyPair = keyPairGen.generateKeyPair();
    }
}
