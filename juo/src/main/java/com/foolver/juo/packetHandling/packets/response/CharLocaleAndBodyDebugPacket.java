package com.foolver.juo.packetHandling.packets.response;

public class CharLocaleAndBodyDebugPacket extends AbstractResponsePacket {

  @Override
  public void fillBuffer() {
    buffer.put((byte) 0x1B); // draw game player
    buffer.putInt(0); // creatureId
    buffer.putInt(0); // unknown
    buffer.putShort((short) 6); // bodyType
    buffer.putShort((short) 2000); // xLoc
    buffer.putShort((short) 4980); // yLoc
    buffer.put((byte) 0x00); // unknown
    buffer.put((byte) 0x00); // zloc
    buffer.put((byte) 0x00); // direction
    buffer.putInt(0); // unknown
    buffer.putInt(0); // unknown
    buffer.put((byte) 0x00); // unknown
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
