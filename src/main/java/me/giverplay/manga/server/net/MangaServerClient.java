package me.giverplay.manga.server.net;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MangaServerClient {

  private final InetSocketAddress address;
  private final ServerNetworkHandler handler;
  private final String name;

  public MangaServerClient(ServerNetworkHandler handler, InetSocketAddress address) {
    this.name = address.getHostName() + ":" + address.getPort();
    this.handler = handler;
    this.address = address;
  }

  public void sendData(byte[] data) throws IOException {
    handler.sendData(address, data);
  }

  public InetSocketAddress getAddress() {
    return address;
  }

  public String getName() {
    return name;
  }

  public ServerNetworkHandler getHandler() {
    return handler;
  }
}
