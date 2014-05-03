package com.foolver.juo.packetHandling.packets.response;

import com.foolver.juo.packetHandling.packets.utils.Direction;

public class DrawGamePlayerPacket extends AbstractResponsePacket {

  private int serialId;
  private short xLoc;
  private short yLoc;
  private byte zLoc;
  private Direction dir;

  public DrawGamePlayerPacket(int serialId, short xLoc, short yLoc, byte zLoc, Direction dir) {
    super(false);
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    this.zLoc = zLoc;
    this.dir = dir;
    allocateAndSetupBuffer();
  }

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x20); // packetId
    buffer.putInt(serialId); // playerId
    buffer.putShort((short) 400); // model - bodytype
    buffer.put((byte)0x00); // unknown
    buffer.putShort((short)33770); // skin color / hue
    buffer.put((byte)0x01); // flag byte
    buffer.putShort(xLoc); // xloc
    buffer.putShort(yLoc); // yloc
    buffer.putShort((short) 0); // unknown
    buffer.put(dir.getValue()); // direction
    buffer.put(zLoc); // zloc
  }

  @Override
  public int getBufferSize() {
    return 19;
  }

}
