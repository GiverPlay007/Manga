package me.giverplay.manga.net;

import me.giverplay.manga.server.net.MangaServerClient;
import me.giverplay.manga.utils.ExceptionBiConsumer;

import java.io.IOException;
import java.net.InetSocketAddress;

public interface NetworkHandler {
  void poll();

  void sendData(InetSocketAddress address, byte[] data) throws IOException;

  void onPacketReceived(ExceptionBiConsumer<MangaServerClient, byte[]> callback);
}
