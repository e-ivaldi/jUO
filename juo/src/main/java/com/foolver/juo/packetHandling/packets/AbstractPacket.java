package com.foolver.juo.packetHandling.packets;

import java.nio.ByteBuffer;

public abstract class AbstractPacket implements Packet {

  protected ByteBuffer buffer;

  public AbstractPacket() {
    buffer = ByteBuffer.allocate(getBufferSize());
    fillBuffer();
  }

  public byte[] getBytes() {
    return buffer.array();
  }

  public boolean hasBytes() {
    return getBytes().length > 0;
  }

  protected abstract void fillBuffer();
  
  public abstract int getBufferSize();

}
