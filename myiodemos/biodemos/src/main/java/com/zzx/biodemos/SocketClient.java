package com.zzx.biodemos;

import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 100; i++) {
                Socket socket = new Socket("localhost", 8888);
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.print("hello 张三丰" + i);
                printWriter.close();
                socket.close();
                Thread.sleep(1000);
            }
        } catch (Exception e) {

        }
    }
}
