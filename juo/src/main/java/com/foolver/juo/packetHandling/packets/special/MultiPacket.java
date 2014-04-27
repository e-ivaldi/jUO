package com.foolver.juo.packetHandling.packets.special;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.response.AbstractResponsePacket;

public class MultiPacket extends AbstractResponsePacket {

  private Packet[] packets;

  public MultiPacket(Packet... packets) {
    super(false);
    this.packets = packets;
    this.allocateAndSetupBuffer();
  }

  @Override
  public void fillBuffer() {
    for (int i = 0; i < packets.length; i++) {
      buffer.put(packets[i].getBytes());
    }
  }

  @Override
  public int getBufferSize() {
    int size = 0;
    for (int i = 0; i < packets.length; i++) {
      size += packets[i].getBytes().length;
    }
    return size;
  }
}
