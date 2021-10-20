package me.giverplay.manga.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class Packet {
  private final int id;

  public Packet(int id) {
    this.id = id;
  }

  public abstract void serialize(DataOutputStream data);

  public abstract void deserialize(DataInputStream data);

  public int getId() {
    return id;
  }
}
