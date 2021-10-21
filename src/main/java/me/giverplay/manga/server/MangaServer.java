package me.giverplay.manga.server;

import me.giverplay.manga.net.PacketHandler;
import me.giverplay.manga.server.net.ServerPacketHandlerUDP;

import java.net.DatagramSocket;
import java.net.SocketException;

public class MangaServer implements Runnable {

  private DatagramSocket socket;
  private PacketHandler handler;

  private boolean isRunning;

  public MangaServer(int port) {
    try {
      socket = new DatagramSocket(port);
    } catch (SocketException e) {
      e.printStackTrace();
    }

    handler = new ServerPacketHandlerUDP(socket);

    handler.onPacketReceived((id, data) -> {
      String msg = data.readUTF();
      System.out.println(msg);
    });

    new Thread(this, "Server").start();
  }

  @Override
  public void run() {
    isRunning = true;

    while(isRunning) {
      handler.poll();
    }
  }
}
