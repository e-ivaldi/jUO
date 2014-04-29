package com.foolver.juo.packetHandling.packets.shared;

import com.foolver.juo.packetHandling.packets.response.AbstractResponsePacket;

public class PingMessagePacket extends AbstractResponsePacket {

  private byte sequence;

  public PingMessagePacket(byte sequence) {
    super(false);
    this.sequence = sequence;
    allocateAndSetupBuffer();
  }

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x73); // packetId
    buffer.put(sequence);
  }

  @Override
  public int getBufferSize() {
    return 2;
  }

  public byte getSequence() {
    return sequence;
  }

}
