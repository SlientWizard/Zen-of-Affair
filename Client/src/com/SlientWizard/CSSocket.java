package com.SlientWizard;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

// An abstract for socket IO established between client and server
public abstract class CSSocket {
    protected Socket socket;
    protected BufferedReader in;
    protected PrintWriter writer;
    protected BufferedReader br;
    protected ObjectInputStream objectIn;
    protected ObjectOutputStream objectOut;

    public CSSocket(Socket inputSocket)throws IOException
    {
        socket = inputSocket;

        // String Msg Transport
        // input from remote socket object
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // writer for remote message sending
        writer = new PrintWriter(socket.getOutputStream());
        // buffer for input
        br = new BufferedReader(new InputStreamReader(System.in));

        // Object Transport
        // socket ObjectTransport class must be created in different order in client and server, or will block
        // object output
        objectOut = new ObjectOutputStream(socket.getOutputStream());
        // object input
        objectIn = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    // A standard function for socket communication
    public abstract void startSocket();

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

    public Object getObject() throws IOException, ClassNotFoundException
    {
        // Get a object and then deserialization
        return objectIn.readObject();
    }


    public void sendObject(Object object) throws IOException
    {
        // Serialize a object and then send it
        objectOut.writeObject(object);
        objectOut.flush();
    }

    // Close resource
    public void close() throws IOException {
        writer.close();     // Close socket output stream
        in.close();         // Close socket input stream
        objectIn.close();   // Close socket object input stream
        objectOut.close();  // Close socket object output stream
        socket.close();     // Close socket
    }
}
