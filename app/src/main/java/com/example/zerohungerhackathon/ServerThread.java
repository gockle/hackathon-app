package com.example.zerohungerhackathon;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket clientSocket = serverSocket.accept();
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            // Read from input stream
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String receivedMessage = new String(buffer, 0, bytesRead);
                // Process the received message
            }

            // Send a message
            String message = "Hello from server";
            outputStream.write(message.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
