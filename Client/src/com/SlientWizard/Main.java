package com.SlientWizard;

import java.io.IOException;

public class Main {

    public static void main(String[] args)
    {
	// write your code here
        try {
            socketClient myClient = new socketClient("127.0.0.1",10241);
            myClient.startSocket();
            myClient.close();
        }catch(IOException e) {
            System.out.println(e);
        }
    }
}
