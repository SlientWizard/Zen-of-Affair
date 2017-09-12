package com.SlientWizard;

import java.io.IOException;

public abstract class ServerVerify {
    protected CSSocket socket;
    public ServerVerify(CSSocket socket)
    {
        this.socket = socket;
    }
    abstract void verifyServer()throws IOException;
}
