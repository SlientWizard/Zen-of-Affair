package com.SlientWizard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(10241);
            System.out.println("SocketServer is waiting");
            Socket socket = serverSocket.accept();
            socketService mySocket = new socketService(socket);

            Object obj = mySocket.getObject();
            System.out.println(obj);
            mySocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}