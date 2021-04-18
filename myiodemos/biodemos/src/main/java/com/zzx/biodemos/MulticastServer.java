package com.zzx.biodemos;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.TimeUnit;

public class MulticastServer {
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("224.8.8.8");
            MulticastSocket socket = new MulticastSocket();
            for (int i = 0; i < 100000000; i++) {
                String data = "hello world" + i;
                byte[] bytes = data.getBytes();
                socket.send(new DatagramPacket(bytes, bytes.length, group, 8888));
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (Exception e) {

        }
    }
}
