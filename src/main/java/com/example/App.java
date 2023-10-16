package com.example;
import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main( String[] args ){
        try {
            ServerSocket server = new ServerSocket(2750);
            while(true){
                Socket s = server.accept();
                ServerThread thread = new ServerThread(s);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
