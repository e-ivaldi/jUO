package com.foolver.juo.packetHandling.packets.response;

public class WrongCredentialsPacket extends AbstractResponsePacket {

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x53);
    buffer.put((byte) 0x00);
  }

  @Override
  public int getBufferSize() {
    return 2;
  }

}
