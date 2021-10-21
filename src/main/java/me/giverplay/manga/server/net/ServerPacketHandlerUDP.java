package me.giverplay.manga.server.net;

import me.giverplay.manga.net.Packet;
import me.giverplay.manga.net.PacketHandler;
import me.giverplay.manga.utils.ExceptionBiConsumer;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerPacketHandlerUDP implements PacketHandler {

  private final DatagramSocket socket;
  private final byte[] buffer = new byte[Short.MAX_VALUE];

  private ExceptionBiConsumer<Integer, DataInputStream> callback;
  private DatagramPacket message;

  public ServerPacketHandlerUDP(DatagramSocket socket) {
    this.socket = socket;
  }

  @Override
  public void poll() {
    message = new DatagramPacket(buffer, buffer.length);

    try {
      socket.receive(message);
      processData(new DataInputStream(new ByteArrayInputStream(buffer, 0, message.getLength())));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void sendPacket(Packet packet) {

  }

  @Override
  public void onPacketReceived(ExceptionBiConsumer<Integer, DataInputStream> callback) {
    this.callback = callback;
  }

  private void processData(DataInputStream data) throws Exception {
    int id = data.readInt();

    if(callback != null) {
      callback.accept(id, data);
    }
  }
}
