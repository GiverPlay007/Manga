package me.giverplay.manga.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class MangaClient {
  public MangaClient(int port, String message) throws Exception {
    DatagramSocket socket = new DatagramSocket();
    InetAddress addr = InetAddress.getByName("localhost");

    byte[] buffer = message.getBytes(StandardCharsets.UTF_8);

    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, addr, port);
    socket.send(packet);
    socket.close();
  }
}
