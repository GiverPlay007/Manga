package me.giverplay.manga.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class MangaClient {
  public MangaClient(int port, String message) throws Exception {
    DatagramSocket socket = new DatagramSocket();
    InetAddress addr = InetAddress.getByName("localhost");

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(bytes);

    out.writeInt(10);
    out.writeUTF(message);

    byte[] array = bytes.toByteArray();
    DatagramPacket packet = new DatagramPacket(array, array.length, addr, port);

    socket.send(packet);
    socket.close();
  }
}
