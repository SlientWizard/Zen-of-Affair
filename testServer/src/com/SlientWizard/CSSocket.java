package com.SlientWizard;

import java.io.IOException;

public interface CSSocket {
    void startSocket();
    String getMsg() throws IOException;
    void sendMsg(String line) throws IOException;
    void close() throws IOException;
}
