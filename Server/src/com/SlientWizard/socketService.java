package com.SlientWizard;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class socketService extends CSSocket
{
    // Unit test code
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(10241);
            System.out.println("SocketServer is waiting");
            socketService mySocket = new socketService(serverSocket.accept());
            System.out.println("A client connected");
            mySocket.startSocket();
            mySocket.close();
        }catch(IOException e) {
            System.out.println(e);
        }
    }

    public socketService(Socket inputSocket)throws IOException
    {
        super(inputSocket);
        System.out.println("A socketService is running");
    }

    // A standard function for socket communication
    public void startSocket()
    {
        String serverLine;
        String clientLine;
        try {
            do {
                // Attention: service should wait for the msg from client
                clientLine = getMsg();
                System.out.println("Client:" + clientLine);
                serverLine = br.readLine();
                sendMsg(serverLine);
                System.out.println("Server:" + serverLine);
            }while(!Objects.equals(serverLine, "end"));
        }catch(IOException e)
        {
            System.out.println(e);
        }
    }
}
