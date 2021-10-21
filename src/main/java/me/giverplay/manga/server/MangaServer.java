package me.giverplay.manga.server;

import me.giverplay.manga.net.NetworkHandler;
import me.giverplay.manga.server.net.ServerNetworkHandler;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class MangaServer implements Runnable {

  private DatagramSocket socket;
  private NetworkHandler handler;

  private boolean isRunning;

  public MangaServer(int port) {
    try {
      socket = new DatagramSocket(port);
    } catch (SocketException e) {
      e.printStackTrace();
    }

    handler = new ServerNetworkHandler(socket);

    handler.onPacketReceived((data) -> {
      String msg = new String(data, StandardCharsets.UTF_8);
      System.out.println("[Server] " + msg);
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
