package com.foolver.juo.packetHandling.packets.request;

public class SingleClickPacket extends AbstractRequestPacket {

  private int objectId;

  public SingleClickPacket(int objectId) {
    this.objectId = objectId;
  }

  public int getObjectId() {
    return objectId;
  }

}
