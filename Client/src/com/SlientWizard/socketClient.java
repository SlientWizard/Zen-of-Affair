package com.SlientWizard;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class socketClient extends CSSocket{

    // Unit test code
    public static void main(String[] args)
    {
        try {
            socketClient myClient = new socketClient(new Socket("127.0.0.1",10241));
            myClient.startSocket();
            myClient.close();
        }catch(IOException e) {
            System.out.println(e);
        }
    }

    public socketClient(Socket inputSocket)throws IOException
    {
        super(inputSocket);
        System.out.println("Client is running");
    }

    public void startSocket() {
        String serverLine;
        String clientLine;
        try {
            clientLine = br.readLine();
            sendMsg(clientLine);
            while(!Objects.equals(clientLine, "end")){
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
}
