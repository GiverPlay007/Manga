package me.giverplay.manga.net;

import me.giverplay.manga.utils.ExceptionBiConsumer;

import java.io.DataInputStream;

public interface PacketHandler {
  void poll();

  void sendPacket(Packet packet);

  void onPacketReceived(ExceptionBiConsumer<Integer, DataInputStream> callback);
}
