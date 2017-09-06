package com.SlientWizard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class Main {

    public static void main(String[] args)
    {
	// write your code here
        try {
            Socket socket = new Socket("127.0.0.1",10241);
            /*
            socketClient myClient = new socketClient(new Socket("127.0.0.1",10241));
            //HashMap<String,String> map = new HashMap<String, String>();
            //map.put("MsgType","Test");
            myClient.getObject();
            myClient.sendObject(new EncryptMsg());
            myClient.close();
            */
            // object input
            ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
            // object output
            ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("end");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
