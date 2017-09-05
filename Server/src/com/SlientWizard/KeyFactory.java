package com.SlientWizard;

// Design Pattern : Factory Function Pattern
// Usage: extend different classes to create keys according to different input
//        and algorithm
public abstract class KeyFactory {
    //protected CSSocket socket;
    /*public KeyFactory(CSSocket inputSocket)
    {
        socket = inputSocket;
    }*/
    // Generate key based on random number
    abstract String[] createNonSymetricKeys();
    abstract String[] createSymetricKeys();
    // Calc Key according to the the big int
    abstract String[] createNonSymmetricKeys(String base,String index);
    abstract String[] createSymmetricKeys(String base,String index);

}
