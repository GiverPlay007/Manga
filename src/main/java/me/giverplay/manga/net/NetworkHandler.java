package me.giverplay.manga.net;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.function.Consumer;

public interface NetworkHandler {
  void poll();

  void sendData(SocketAddress address, byte[] data) throws IOException;

  void onPacketReceived(Consumer<byte[]> callback);
}
