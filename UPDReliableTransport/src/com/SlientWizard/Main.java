package com.SlientWizard;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException {
        //InetAddress address = InetAddress.getLocalHost();
        InetAddress address = InetAddress.getByName("10.170.14.16");
        System.out.print(address);
    }
}
