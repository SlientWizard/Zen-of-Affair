package com.SlientWizard;

import javax.crypto.EncryptedPrivateKeyInfo;

public class DefaultNegotiation extends Negotiation {

    DefaultNegotiation(CSSocket socket )
    {
        super(socket);
    }

    public void negotiate() throws Exception {

    }

    public DHKeyExchangeAgreement getDHKeyExchangeAgreement() {
        return null;
    }

    public KeyFactory getKeyFactory() {
        return null;
    }

    public ClientVerify getClientVerify() {
        return null;
    }
}
