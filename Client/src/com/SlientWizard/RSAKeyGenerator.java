package com.SlientWizard;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSAKeyGenerator implements KeyGenerator{

    public KeyPair keyPair;
    public static final String KEY_ALGORITHM = "RSA";

    public PublicKey getPublicKey() //throws Exception
    {
        return keyPair.getPublic();
    }

    public PrivateKey getPrivateKey() //throws Exception
    {
        return keyPair.getPrivate();
    }

    public void generateKey() throws Exception
    {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        keyPair = keyPairGen.generateKeyPair();
    }
}
