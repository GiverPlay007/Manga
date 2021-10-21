package me.giverplay.manga;

import me.giverplay.manga.client.MangaClient;
import me.giverplay.manga.server.MangaServer;

public class Main {
  public static void main(String[] args) throws Exception {
    MangaServer server = new MangaServer(3000);

    MangaClient client1 = new MangaClient(3000, "Giver", "Boa sorte!");
    MangaClient client2 = new MangaClient(3000, "Will", "Tudo jóia?");
    MangaClient client3 = new MangaClient(3000, "LaVolpe", "Quer jogar?");
    MangaClient client4 = new MangaClient(3000, "Giverzin", "Boa noite!");
    MangaClient client5 = new MangaClient(3000, "Notezin", "Eu gostaria");
    MangaClient client6 = new MangaClient(3000, "Souder", "Que tal café?");
    MangaClient client7 = new MangaClient(3000, "Woody", "Humm, abacaxi");
    MangaClient client8 = new MangaClient(3000, "Fruitman", "Salada de frutas!");
  }
}
