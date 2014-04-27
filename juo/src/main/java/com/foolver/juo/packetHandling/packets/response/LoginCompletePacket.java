package com.foolver.juo.packetHandling.packets.response;

public class LoginCompletePacket extends AbstractResponsePacket {

  @Override
  public void fillBuffer() {
    buffer.put((byte) 0x55);
  }

  @Override
  public int getBufferSize() {
    return 1;
  }

}
