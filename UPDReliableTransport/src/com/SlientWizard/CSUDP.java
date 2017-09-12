package com.SlientWizard;

import java.io.IOException;
import java.net.*;

public class CSUDP {
    protected DatagramSocket socket;
    protected DatagramPacket receivePacket;
    protected DatagramPacket sendPacket;
    protected InetAddress aimAddress;
    protected byte[] receiveMsg;  // Byte array for message receive buffer
    protected byte[] sendMsg;

    // Create a UDP socket
    CSUDP(int localPort) throws UnknownHostException, SocketException {
        // create a UDP socket
        socket = new DatagramSocket(localPort);
        receiveMsg = new byte[1024];
        receivePacket = new DatagramPacket(receiveMsg, receiveMsg.length);
    }

    public void sendMsg(String addr,int port,String Msg) throws UnknownHostException {
        aimAddress = InetAddress.getByName(addr);
        sendMsg = Msg.getBytes();
        sendPacket = new DatagramPacket(sendMsg,sendMsg.length,aimAddress,port);

    }
    public String getMsg() throws IOException {
        socket.receive(receivePacket);
        return new String(receiveMsg, 0,receiveMsg.length);
    }
}
