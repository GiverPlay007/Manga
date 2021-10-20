package me.giverplay.manga.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class MangaServer implements Runnable {
  private DatagramSocket socket;
  private volatile boolean isRunning;

  private byte[] buffer = new byte[1024];

  public MangaServer(int port) {
    try {
      socket = new DatagramSocket(port);
    } catch (SocketException e) {
      e.printStackTrace();
    }

    new Thread(this, "Server").start();
  }

  @Override
  public void run() {
    isRunning = true;

    while(isRunning) {
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

      try {
        socket.receive(packet);
      } catch (IOException e) {
        e.printStackTrace();
      }

      String msg = new String(packet.getData(), 0, packet.getLength());

      if(msg.equals("stop")) {
        isRunning = false;
        socket.close();
        return;
      }

      System.out.println("Server -> Received: " + msg);
    }
  }
}
