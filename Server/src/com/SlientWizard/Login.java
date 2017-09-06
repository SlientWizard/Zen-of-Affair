package com.SlientWizard;

public class Login {
    private Negotiation negotiation;
    private CSSocket socket;
    private DHKeyExchangeAgreement dhKeyExchangeAgreement;
    private KeyFactory keyFactory;
    private ClientVerify clientVerify;
    private String[] keys;

    // bind a socket for calling it's resource close()
    // bind a negotiation as a selected factory to instantiate other objects
    // user should instantiate a negotiation according to their prefer.
    public Login(CSSocket inputSocket,Negotiation inputNegotiation) {
        CSSocket socket = inputSocket;
        negotiation = inputNegotiation;
    }

    // A master function to coordinate other functions by calling them
    public void start()
    {
        try {
            // Negotiation
            init();
            // DH key exchange agreement
            String bigInt[] = dhKeyExchangeAgreement.getBigInt();
            // including server identity verify
            //dhKeyExchangeAgreement.serverVerify();
            //calc Keys
            //keys = keyFactory.createKeys();
            // client verify
            clientVerify.verify();
            // close socket resources
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Init() will finish negotiate Editions and Encryption between client and server
    // Then negotiation will product objects according to the result of negotiation
    private void init() throws Exception
    {
        // Negotiate Editions and Encryption
        negotiation.negotiate();
        // instantiate objects according to Negotiation
        dhKeyExchangeAgreement = negotiation.getDHKeyExchangeAgreement();
        keyFactory = negotiation.getKeyFactory();
        clientVerify = negotiation.getClientVerify();
    }

    public String[] getKeys()
    {
        return keys;
    }
}