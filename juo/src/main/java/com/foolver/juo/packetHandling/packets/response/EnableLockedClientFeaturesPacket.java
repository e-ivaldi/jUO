package com.foolver.juo.packetHandling.packets.response;

public class EnableLockedClientFeaturesPacket extends AbstractResponsePacket {

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x89);
    buffer.putShort((short) 0x01);

  }

  @Override
  public int getBufferSize() {
    return 3;
  }

}
