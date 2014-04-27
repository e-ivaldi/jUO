package com.foolver.juo.packetHandling.packets.request;

public class LoginRequestPacket extends AbstractRequestPacket {

  private String username;
  private String password;

  public LoginRequestPacket(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

}
