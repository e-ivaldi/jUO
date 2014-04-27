package com.foolver.juo.packetHandling.packets.response;

import com.foolver.juo.packetHandling.packets.utils.Direction;

public class MovePlayerPacket extends AbstractResponsePacket {

  private Direction dir;

  public MovePlayerPacket(Direction dir) {
    super(false);
    this.dir = dir;
    allocateAndSetupBuffer();
  }

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x97); // packetId
    buffer.put(dir.getValue()); // direction
  }

  @Override
  public int getBufferSize() {
    return 2;
  }

}
