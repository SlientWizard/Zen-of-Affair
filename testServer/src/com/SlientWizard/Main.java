package com.SlientWizard;

public class Main {

    public static void main(String[] args) {
	// write your code here
        socketService socketService = new socketService();
        //1、a)创建一个服务器端Socket，即SocketService
        socketService.oneServer();
    }
}
