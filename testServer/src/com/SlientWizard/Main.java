package com.SlientWizard;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(10241);
            socketService mySocket = new socketService(serverSocket.accept());
            mySocket.startSocket();
            mySocket.close();
        }catch(IOException e) {
            System.out.println(e);
        }
    }
}
