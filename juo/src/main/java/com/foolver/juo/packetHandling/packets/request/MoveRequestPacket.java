package com.foolver.juo.packetHandling.packets.request;

import com.foolver.juo.packetHandling.packets.utils.Direction;

public class MoveRequestPacket extends AbstractRequestPacket {

  Direction direction;
  byte sequenceNumber;
  int fastWalkPreventionKey;

  public MoveRequestPacket(Direction direction, byte sequenceNumber, int fastWalkPreventionKey) {
    this.direction = direction;
    this.sequenceNumber = sequenceNumber;
    this.fastWalkPreventionKey = fastWalkPreventionKey;
  }

  public Direction getDirection() {
    return direction;
  }

  public byte getSequenceNumber() {
    return sequenceNumber;
  }

  public int getFastWalkPreventionKey() {
    return fastWalkPreventionKey;
  }

}
