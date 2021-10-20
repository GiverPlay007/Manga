package me.giverplay.manga.net;

import java.io.DataInputStream;
import java.util.function.BiConsumer;

public interface PacketHandler {
  void poll();

  void sendPacket(Packet packet);

  void onPacketReceived(BiConsumer<Integer, DataInputStream> callback);
}
