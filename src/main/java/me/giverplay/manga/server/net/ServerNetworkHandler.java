package me.giverplay.manga.server.net;

import me.giverplay.manga.net.NetworkHandler;
import me.giverplay.manga.utils.ExceptionBiConsumer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class ServerNetworkHandler implements NetworkHandler {

  private static final Random random = new Random();

  private final HashMap<String, MangaServerClient> clients = new HashMap<>();
  private final byte[] buffer = new byte[Short.MAX_VALUE];

  private final DatagramSocket socket;

  private ExceptionBiConsumer<MangaServerClient, byte[]> callback;

  public ServerNetworkHandler(DatagramSocket socket) {
    this.socket = socket;
  }

  @Override
  public void poll() {
    DatagramPacket message = new DatagramPacket(buffer, buffer.length);

    try {
      socket.receive(message);

      String clientName = message.getAddress().getHostName() + ":" + message.getPort();

      if(!clients.containsKey(clientName)) {
        clients.put(clientName, new MangaServerClient(this, (InetSocketAddress) message.getSocketAddress()));
      }

      MangaServerClient client = clients.get(clientName);

      processData(client, Arrays.copyOf(buffer, message.getLength()));

      if(random.nextInt(100) < 30) {
        sendData(client.getAddress(), ("Olá, " + clientName).getBytes(StandardCharsets.UTF_8));
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void sendData(InetSocketAddress address, byte[] data) throws IOException {
    DatagramPacket packet = new DatagramPacket(data, data.length, address);
    socket.send(packet);
  }

  @Override
  public void onPacketReceived(ExceptionBiConsumer<MangaServerClient, byte[]> callback) {
    this.callback = callback;
  }

  private void processData(MangaServerClient client, byte[] data) throws Exception {
    if(callback != null) {
      callback.accept(client, data);
    }
  }
}
