/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bahad
 */
public class ServerClient extends Thread {

    Socket socket; // blocking
    Server server;
    OutputStream output;// write
    InputStream input; //read
    boolean isListening;

    public ServerClient(Socket socket, Server server) throws IOException {
        this.server = server;
        this.socket = socket;
        this.output = socket.getOutputStream();// write
        this.input = socket.getInputStream(); //read
        this.isListening = false;
        //System.out.println(this.socket.getInetAddress().toString() + ":" + this.socket.getPort() + "-> connected...");
        //ServerScreen.jList_ListClients_model.addElement(this.socket.getInetAddress().toString() + ":" + this.socket.getPort());
    }

    public void Listen() {
        this.isListening = true;
        this.start();
    }

    public void Stop() {
        try {
            this.isListening = false;
            this.socket.close();
            this.input.close();
            this.output.close();
            this.server.removeClient(this); 
        } catch (IOException ex) {
            System.out.println(this.socket.getInetAddress().toString() + ":" + this.socket.getPort() + "-> stopped...");
            //Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SendMessage(byte[] messageBytes) throws IOException {
        this.output.write(messageBytes);
    }

    @Override
    public void run() {         // asenkron olarak calistigi icin sen hatayi firlattiginda kimse tutamaz
        try {
            while (this.isListening) {
                int byteSize = this.input.read(); // Blocking
                byte bytes[] = new byte[byteSize];
                this.input.read(bytes); // buffer gibi
                System.out.println(this.socket.getInetAddress().toString() + ":" + this.socket.getPort() + "-> message reacted...");
                System.out.println(new String(bytes, StandardCharsets.UTF_8));
                ServerScreen.jList_MessagesFromClients_model.addElement(this.socket.getInetAddress().toString() + ":" + this.socket.getPort() + "->" + new String(bytes, StandardCharsets.UTF_8));
            }

        } catch (IOException ex) {
            this.Stop();
            System.out.println(this.socket.getInetAddress().toString() + ":" + this.socket.getPort() + "-> closed...");
            //Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
