package me.giverplay.manga.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class MangaClient implements Runnable {
  private final DatagramSocket socket;
  private final String name;

  public MangaClient(int port, String name, String message) throws Exception {
    this.name = name;

    this.socket = new DatagramSocket();
    InetAddress addr = InetAddress.getByName("localhost");

    byte[] msg = (name + ": " + message).getBytes(StandardCharsets.UTF_8);
    DatagramPacket packet = new DatagramPacket(msg, msg.length, addr, port);

    new Thread(this, name).start();

    socket.send(packet);
  }

  @Override
  public void run() {
    byte[] buffer = new byte[Short.MAX_VALUE];

    while(true) {
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

      try {
        socket.receive(packet);
        String msg = new String(buffer, 0, packet.getLength());
        System.out.println("[" + name + "] Server: " + msg);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
