package com.foolver.juo.packetHandling.packets.request;

public class GameServerLoginPacket extends AbstractRequestPacket {

  private int key;
  private String sid;
  private String password;

  public GameServerLoginPacket(int key, String sid, String password) {
    this.key = key;
    this.sid = sid;
    this.password = password;
  }

  public int getKey() {
    return key;
  }

  public String getSid() {
    return sid;
  }

  public String getPassword() {
    return password;
  }

}
