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
            socketClient myClient = new socketClient(socket);   
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("MsgType","Test");
            myClient.sendObject(map);
            myClient.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
