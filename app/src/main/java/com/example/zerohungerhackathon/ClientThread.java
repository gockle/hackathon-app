package com.example.zerohungerhackathon;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class ClientThread extends Thread {
    private String hostAddress;
    private Socket socket;
    private OutputStream outputStream;
    private Handler handler;

    public ClientThread(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(hostAddress, 8888);
            outputStream = socket.getOutputStream();

            Looper.prepare();
            handler = new Handler(Looper.myLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    try {
                        String message = (String) msg.obj;
                        outputStream.write(message.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            Looper.loop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        if (handler != null) {
            Message msg = handler.obtainMessage();
            msg.obj = message;
            handler.sendMessage(msg);
        }
    }
}
