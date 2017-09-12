package com.SlientWizard;

import java.security.PrivateKey;
import java.security.PublicKey;

// Design Pattern : Factory Function Pattern
// Usage: extend different classes to create keys according to different input
//        and algorithm
public interface KeyGenerator {
    void generateKey() throws Exception;
    PublicKey getPublicKey();
    PrivateKey getPrivateKey();
}
