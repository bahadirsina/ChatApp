/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.chatserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bahad
 */
public class ChatServer {

    public static void main(String[] args) throws InterruptedException {
        
        Server server = new Server(5000);
        server.Listen();
        sleep(20000);
                
//        int port = 5000;
//        ServerSocket serverSocket;
//        try {
//            serverSocket = new ServerSocket(port); // Ip degerine ihtiyac yok zaten internetten ip degeri o cihazda cekilmektedir.
//            while (!serverSocket.isClosed()) {
//                Socket clientSocket = serverSocket.accept(); // client baglantisi bekleme - Blocking asenkron programlama olsaydi blocking olmazdi.
//
//                System.out.println("ip = " + clientSocket.getInetAddress().toString());
//                System.out.println("port = " + clientSocket.getPort());
//
//                OutputStream clientOutput = clientSocket.getOutputStream();// write
//                InputStream clientInput = clientSocket.getInputStream(); //read
//                int byteSize = clientInput.read(); // Blocking
//                byte bytes[] = new byte[byteSize]; 
//                clientInput.read(bytes); // buffer gibi
//
//                System.out.println(new String(bytes, StandardCharsets.UTF_8));
//
//                clientSocket.close();
//                clientOutput.close();
//                clientInput.close();
//            }
//
//        } catch (IOException ex) {
//            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
