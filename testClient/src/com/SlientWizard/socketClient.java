package com.SlientWizard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class socketClient implements CSSocket{
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter writer = null;
    private BufferedReader br = null;

    public socketClient(String address,int port)throws IOException
    {
        // Create client socket
        socket = new Socket(address, port);
        System.out.println("Client is running");
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void startSocket() {
        String serverLine = null;
        String clientLine = null;
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

    public void sendMsg(String line) throws IOException
    {
        writer.println(line);
        writer.flush();
    }

    public String getMsg() throws IOException
    {
        // Get Server input msg
        return in.readLine();
    }

    public void close() throws IOException
    {
        //4、关闭资源
        writer.close(); // 关闭Socket输出流
        in.close(); // 关闭Socket输入流
        socket.close(); // 关闭Socket
    }

}
