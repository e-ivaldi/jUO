package com.foolver.juo.packetHandling.packets.request;

public class RequestPlayerStatusPacket extends AbstractRequestPacket {

  private int playerSerial;
  private byte status;

  public RequestPlayerStatusPacket(int playerSerial, byte status) {
    this.playerSerial = playerSerial;
    this.status = status;
  }

  public int getPlayerSerial() {
    return playerSerial;
  }

  public byte getStatus() {
    return status;
  }

}
