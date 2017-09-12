package com.SlientWizard;

import java.net.Socket;

// Design Pattern : Abstract Factory Pattern
// Negotiate Editions and Encryption method between client and server
// Then instantialize proper objects according to negotiation result
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
    public abstract DHKeyExchangeAgreement getDHKeyExchangeAgreement();
    public abstract KeyGenerator getKeyFactory();
    public abstract ClientVerify getClientVerify();
}
