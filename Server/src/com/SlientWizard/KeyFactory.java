package com.SlientWizard;

// Design Pattern : Factory Function Pattern
// Usage: extend different classes to create keys according to different input
//        and algorithm
public abstract class KeyFactory {
    protected CSSocket socket;
    public KeyFactory(CSSocket inputSocket)
    {
        socket = inputSocket;
    }
    // Calc Key according to the the big int
    abstract String[] createKeys(String bigInt);
}
