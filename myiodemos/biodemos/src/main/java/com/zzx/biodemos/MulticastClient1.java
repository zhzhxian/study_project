package com.zzx.biodemos;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MulticastClient1 {
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("224.8.8.8");
            MulticastSocket socket = new MulticastSocket(8888);
            socket.joinGroup(group);
            byte[] buf = new byte[256];
            while (true) {
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                socket.receive(msgPacket);
                String msg = new String(msgPacket.getData());
                System.out.println("客户端1接收到的数据：" + msg);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
