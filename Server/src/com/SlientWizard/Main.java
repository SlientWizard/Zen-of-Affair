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
            /*
            socketService mySocket = new socketService(serverSocket.accept());
            //mySocket.startSocket();
            Object obj = mySocket.getObject();
            System.out.print(obj);
            mySocket.close();
            */
            // object input
            ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
            // object output
            ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("end");
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
}