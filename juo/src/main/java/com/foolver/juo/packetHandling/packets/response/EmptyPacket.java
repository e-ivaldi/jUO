package com.foolver.juo.packetHandling.packets.response;

public class EmptyPacket extends AbstractResponsePacket {

  @Override
  public void fillBuffer() {

  }

  @Override
  public int getBufferSize() {
    return 0;
  }

}
