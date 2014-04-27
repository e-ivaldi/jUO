package com.foolver.juo.packetHandling.packets.request;

public class DeleteCharacterPacket extends AbstractRequestPacket {

  private String password;
  private int charIndex;
  private int clientIp;

  public DeleteCharacterPacket(String password, int charIndex, int clientIp) {
    super();
    this.password = password;
    this.charIndex = charIndex;
    this.clientIp = clientIp;
  }

  public String getPassword() {
    return password;
  }

  public int getCharIndex() {
    return charIndex;
  }

  public int getClientIp() {
    return clientIp;
  }

}
