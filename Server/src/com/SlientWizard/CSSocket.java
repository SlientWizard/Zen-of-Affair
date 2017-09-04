package com.SlientWizard;

import java.io.IOException;

// An interface for socket IO established between client and server
public interface CSSocket {
    void startSocket(); // a communication example
    String getMsg() throws IOException;
    void sendMsg(String Msg) throws IOException;
    void close() throws IOException;   // close resources
}
