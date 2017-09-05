package com.SlientWizard;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(10241);
            System.out.println("SocketServer is waiting");
            socketService mySocket = new socketService(serverSocket.accept());
            Negotiation myNegotiation = new Negotiation(mySocket);
            Login myLogin = new Login(mySocket,myNegotiation);
            myLogin.start();
        }catch(IOException e) {
            System.out.println(e);
        }
    }
}
