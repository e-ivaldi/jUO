package com.foolver.juo.packetHandling.packets.response;

import com.foolver.juo.packetHandling.packets.utils.Direction;

public class CharLocaleAndBodyDebugPacket extends AbstractResponsePacket {

  private short posX;
  private short posY;
  private byte posZ;
  private Direction dir;

  public CharLocaleAndBodyDebugPacket(short posX, short posY, byte posZ, Direction dir) {
    super(false);
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
    this.dir = dir;
    this.allocateAndSetupBuffer();
  }

  @Override
  public void fillBuffer() {
    buffer.put((byte) 0x1B); // draw game player
    buffer.putInt(0); // serial
    buffer.putInt(0); // unknown
    buffer.putShort((short) 400); // bodyType
    buffer.putShort(posX); // xLoc
    buffer.putShort(posY); // yLoc
    buffer.put(posZ); // zloc
    buffer.put(dir.getValue()); // direction
    buffer.put((byte) 0x00); // unknown
    buffer.put((byte) 0xFF);
    buffer.put((byte) 0xFF);
    buffer.put((byte) 0xFF);
    buffer.put((byte) 0xFF);
    buffer.putShort((short) 0);
    buffer.putShort((short) 0);
    buffer.putShort((short) 6144); // map width
    buffer.putShort((short) 4096); // map height
    buffer.putShort((short) 0); // unknown
    buffer.putInt(0); // unknown
  }

  @Override
  public int getBufferSize() {
    return 37;
  }
  
}
