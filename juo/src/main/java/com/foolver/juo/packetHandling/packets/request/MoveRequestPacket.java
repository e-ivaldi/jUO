package com.foolver.juo.packetHandling.packets.request;

public class MoveRequestPacket extends AbstractRequestPacket {

  byte direction;
  byte sequenceNumber;
  int fastWalkPreventionKey;

  public MoveRequestPacket(byte direction, byte sequenceNumber, int fastWalkPreventionKey) {
    this.direction = direction;
    this.sequenceNumber = sequenceNumber;
    this.fastWalkPreventionKey = fastWalkPreventionKey;
  }

  public byte getDirection() {
    return direction;
  }

  public byte getSequenceNumber() {
    return sequenceNumber;
  }

  public int getFastWalkPreventionKey() {
    return fastWalkPreventionKey;
  }

}
