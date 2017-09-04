/*
// Example
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
}*/

package com.SlientWizard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class socketService implements CSSocket
{
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter writer = null;
    private BufferedReader br = null;

    public socketService(Socket inputSocket)throws IOException
    {
        socket = inputSocket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
        br = new BufferedReader(new InputStreamReader(System.in));
    }

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

    public String getMsg() throws IOException
    {
        // Get client input msg
        return in.readLine();
    }

    public void sendMsg(String line) throws IOException
    {
        writer.println(line);
        writer.flush();
    }

    public void close() throws IOException {
        // Close resource
        writer.close(); // Close socket output stream
        in.close(); // Close socket input stream
        socket.close(); // Close socket
    }
}
