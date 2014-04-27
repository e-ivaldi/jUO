package com.foolver.juo.packetHandling.packets.response;

import java.nio.ByteBuffer;

import com.foolver.juo.packetHandling.packets.Packet;

public abstract class AbstractResponsePacket implements Packet {

  protected ByteBuffer buffer;

  public AbstractResponsePacket() {
    this(true);
  }
  
  public AbstractResponsePacket(boolean allocateAndSetupBuffer) {   
    if(allocateAndSetupBuffer){
     allocateAndSetupBuffer();
    }
  }

  protected void allocateAndSetupBuffer(){
    allocateBuffer();
    fillBuffer();
  }
  
  protected void allocateBuffer(){
    buffer = ByteBuffer.allocate(getBufferSize());
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
