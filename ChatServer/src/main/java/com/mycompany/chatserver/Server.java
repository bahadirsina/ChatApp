/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatserver;

import static com.mycompany.chatserver.ServerScreen.jList_ListClients_model;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bahad
 */
public class Server extends Thread {

    ServerSocket serverSocket;
    int port;
    boolean isListening;
    ArrayList<ServerClient> clients;

    public Server(int port) {
        try {
            this.port = port;
            this.serverSocket = new ServerSocket(port);
            this.isListening = false;
            this.clients = new ArrayList<>();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Listen() {
        this.isListening = true;
        this.start();
    }

    public void Stop() {

        try {
            this.isListening = false;
            this.serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AddClient(ServerClient serverClient) {

        this.clients.add(serverClient);
        ServerScreen.jList_ListClients_model.addElement(serverClient.socket.getInetAddress().toString() + ":" + serverClient.socket.getPort());
        ServerScreen.clients_model.addElement(serverClient.socket.getPort());
//        jList_ListClients_model.removeAllElements();
//        for (ServerClient client : clients) {
//            ServerScreen.jList_ListClients_model.addElement(client.socket.getInetAddress().toString() + ":" + client.socket.getPort());
//        }

    }

    public void removeClient(ServerClient serverClient) {

        this.clients.remove(serverClient);
        jList_ListClients_model.removeAllElements();
        for (ServerClient client : clients) {
            ServerScreen.jList_ListClients_model.addElement(client.socket.getInetAddress().toString() + ":" + client.socket.getPort());
        }
    }

    public ServerClient GetClientByIndex(int index) {

        return this.clients.get(index);

    }

    public void SendBroadcast(String message) throws IOException {

        byte[] bytes = (" " + message).getBytes();
        for (ServerClient client : clients) {
            client.SendMessage(bytes);
        }

    }

    public void SendtoClient(String message, int index) throws IOException {
        byte[] bytes = (" " + message).getBytes();
        this.clients.get(index).SendMessage(bytes);
    }

    @Override
    public void run() {

        while (this.isListening) {
            try {
                Socket clientSocket = this.serverSocket.accept(); // blocking
                ServerClient nclient = new ServerClient(clientSocket, this);
                this.AddClient(nclient);
                nclient.Listen();

//                System.out.println("ip = " + clientSocket.getInetAddress().toString());
//                System.out.println("port = " + clientSocket.getPort());
//
//                OutputStream clientOutput = clientSocket.getOutputStream();// write
//                InputStream clientInput = clientSocket.getInputStream(); //read
//                
//                int byteSize = clientInput.read(); // Blocking
//                byte bytes[] = new byte[byteSize];
//                clientInput.read(bytes); // buffer gibi
//
//                System.out.println(new String(bytes, StandardCharsets.UTF_8));
//
//                clientSocket.close();
//                clientOutput.close();
//                clientInput.close();
            } catch (IOException ex) {
                System.out.println("Server is closed...");    
               // Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
