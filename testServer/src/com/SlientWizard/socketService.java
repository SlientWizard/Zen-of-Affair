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

public class socketService
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
        try {
            String serverLine = null;
            String clientLine = null;
            do {
                serverLine = readServer();
                clientLine = readClient();
                writer.println(serverLine);
                writer.flush();
                System.out.println("Server:" + serverLine);
                System.out.println("Client:" + clientLine);
            }while(!Objects.equals(clientLine, "bye"));
        }catch(IOException e)
        {
            System.out.println(e);
        }
    }

    private String readClient() throws IOException
    {
        //3、获取输入流，并读取客户端信息
        return in.readLine();
    }

    private String readServer() throws IOException {
        String line;
        line = br.readLine();
        return line;
    }

    public void close() throws IOException {
        //5、关闭资源
        writer.close(); //关闭Socket输出流
        in.close(); //关闭Socket输入流
        socket.close(); //关闭Socket
    }
}
