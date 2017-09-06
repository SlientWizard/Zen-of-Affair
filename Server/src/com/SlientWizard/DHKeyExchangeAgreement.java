package com.SlientWizard;

// D - H Key Exchange Agreement
// Usage: To ensure the key will not leak when exchange between client and server.
//        Based on Big integer decomposition problem.(Discrete logarithmic problem)
//        At the same time, this agreement will verify the identity of server by
//        it's private key related to it's certificate
public abstract class DHKeyExchangeAgreement {
    protected CSSocket socket;
    protected ServerVerify serverVerify;
    public DHKeyExchangeAgreement(CSSocket inputSocket)
    {
        socket = inputSocket;
        //serverVerify = new ServerVerify(socket);
    }
    // Get P/Pb from client or get Pa from server
    abstract String[] getBigInt();
    // Verify the identity of server
    //void setServerVerify(){serverVerify.verifyServer();}
}
