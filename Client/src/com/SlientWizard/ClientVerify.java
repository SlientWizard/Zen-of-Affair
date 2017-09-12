package com.SlientWizard;

// Usage : verify the identify of client by it's private key
public abstract class ClientVerify {
    protected CSSocket socket;
    public ClientVerify(CSSocket inputSocket)
    {
        socket = inputSocket;
    }
    // verify the identity of client
    abstract void verify() throws Exception;
}
