package me.giverplay.manga.server.net;

import me.giverplay.manga.net.NetworkHandler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class ServerNetworkHandler implements NetworkHandler {

  private static final Random random = new Random();

  private final DatagramSocket socket;
  private final byte[] buffer = new byte[Short.MAX_VALUE];

  private Consumer<byte[]> callback;

  public ServerNetworkHandler(DatagramSocket socket) {
    this.socket = socket;
  }

  @Override
  public void poll() {
    DatagramPacket message = new DatagramPacket(buffer, buffer.length);

    try {
      socket.receive(message);
      processData(Arrays.copyOf(buffer, message.getLength()));

      if(random.nextInt(100) < 30) {
        sendData(message.getSocketAddress(), ("Olá, " + message.getAddress().getHostName()).getBytes(StandardCharsets.UTF_8));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void sendData(SocketAddress address, byte[] data) throws IOException {
    DatagramPacket packet = new DatagramPacket(data, data.length, address);
    socket.send(packet);
  }

  @Override
  public void onPacketReceived(Consumer<byte[]> callback) {
    this.callback = callback;
  }

  private void processData(byte[] data) {
    if(callback != null) {
      callback.accept(data);
    }
  }
}
