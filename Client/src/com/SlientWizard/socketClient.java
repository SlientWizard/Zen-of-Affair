package com.SlientWizard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class socketClient implements CSSocket{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter writer;
    private BufferedReader br;

    // Unit test code
    public static void main(String[] args)
    {
        try {
            socketClient myClient = new socketClient("127.0.0.1",10241);
            myClient.startSocket();
            myClient.close();
        }catch(IOException e) {
            System.out.println(e);
        }
    }

    public socketClient(String address,int port)throws IOException
    {
        // Create client socket
        socket = new Socket(address, port);
        System.out.println("Client is running");
        // input from remote socket object
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // writer for remote message sending
        writer = new PrintWriter(socket.getOutputStream());
        // buffer for input
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void startSocket() {
        String serverLine;
        String clientLine;
        try {
            clientLine = br.readLine();
            sendMsg(clientLine);
            while(!Objects.equals(clientLine, "bye")){
                System.out.println("Client:" + clientLine);
                serverLine = getMsg();
                System.out.println("Server:" + serverLine);
                clientLine = br.readLine();
                sendMsg(clientLine);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void sendMsg(String Msg) throws IOException
    {
        // Use flush to push message immediately
        writer.println(Msg);
        writer.flush();
    }

    public String getMsg() throws IOException
    {
        // Get Server input msg
        return in.readLine();
    }

    public void sendObject(Object object) throws IOException{}
    public Object getObject() throws IOException{}

    // Close resource
    public void close() throws IOException {
        writer.close(); // Close socket output stream
        in.close(); // Close socket input stream
        socket.close(); // Close socket
    }
}
