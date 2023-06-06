/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.chatclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bahad
 */
public class ChatClient {

    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 5000); // "localhost" Client portlari dinamik olarak ayrilir
            OutputStream clientOutput = clientSocket.getOutputStream();// write
            InputStream clientInput = clientSocket.getInputStream(); //read

            byte bytes[] = " Merhaba".getBytes(); // Blocking
            clientOutput.write(bytes);
            while (!clientSocket.isClosed()) {
                int byteSize = clientInput.read(); // Blocking
                bytes = new byte[byteSize];
                clientInput.read(bytes); // buffer gibi
                System.out.println(new String(bytes, StandardCharsets.UTF_8));
            }
//            bytes = "  Nasilsin".getBytes(); // Blocking
//            clientOutput.write(bytes);
//            sleep(20000);
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
//         catch (InterruptedException ex) {
//            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
