package com.SlientWizard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class socketService implements CSSocket
{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter writer;
    private BufferedReader br;

    // Unit test code
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(10241);
            System.out.println("SocketServer is waiting");
            socketService mySocket = new socketService(serverSocket.accept());
            mySocket.startSocket();
            mySocket.close();
        }catch(IOException e) {
            System.out.println(e);
        }
    }

    public socketService(Socket inputSocket)throws IOException
    {
        socket = inputSocket;
        // input from remote socket object
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // writer for remote message sending
        writer = new PrintWriter(socket.getOutputStream());
        // buffer for input
        br = new BufferedReader(new InputStreamReader(System.in));
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

    public String getMsg() throws IOException
    {
        // Get client input msg
        return in.readLine();
    }

    public void sendMsg(String Msg) throws IOException
    {
        // Use flush to push message immediately
        writer.println(Msg);
        writer.flush();
    }

    // Close resource
    public void close() throws IOException {
        writer.close(); // Close socket output stream
        in.close(); // Close socket input stream
        socket.close(); // Close socket
    }
}
