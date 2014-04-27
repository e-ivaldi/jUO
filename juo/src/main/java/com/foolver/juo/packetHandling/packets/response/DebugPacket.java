package com.foolver.juo.packetHandling.packets.response;

import java.nio.ByteBuffer;

public class DebugPacket extends AbstractResponsePacket {
  
  private byte[] bytes;
  
  public DebugPacket(ByteBuffer buffer){
    this(buffer.array());
  }
  
  public DebugPacket(byte... bytes){
    super(false);
    this.bytes = bytes;
    this.allocateAndSetupBuffer();
  }
  
  @Override
  public void fillBuffer() {
    buffer.put(bytes);
  }

  @Override
  public int getBufferSize() {
    return bytes.length;
  }

}
