package com.SlientWizard;

// Design Pattern : Abstract Factory Pattern
// Negotiate Editions and Encryption method between client and server
// Then instantiate proper objects according to negotiation result
public abstract class Negotiation
{
    protected CSSocket socket;
    Negotiation(CSSocket inputSocket)
    {
        socket = inputSocket;
    }
    // Negotiate between server and client. Decide which class to instantialize
    public abstract void negotiate() throws Exception;
    // Following functions is factory functions
    // Set as default
    public abstract DHKeyExchangeAgreement getDHKeyExchangeAgreement();
    public abstract KeyFactory getKeyFactory();
    public abstract ClientVerify getClientVerify();
}
