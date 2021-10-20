package me.giverplay.manga;

import me.giverplay.manga.client.MangaClient;
import me.giverplay.manga.server.MangaServer;

public class Main {
  public static void main(String[] args) throws Exception {
    MangaServer server = new MangaServer(3000);

    MangaClient client1 = new MangaClient(3000, "Olá");
    MangaClient client2 = new MangaClient(3000, "Bom dia");
    MangaClient client3 = new MangaClient(3000, "Como vai?");
    MangaClient client4 = new MangaClient(3000, "Beleza");

    MangaClient end = new MangaClient(3000, "stop");
  }
}
