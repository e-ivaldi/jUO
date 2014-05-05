package com.foolver.juo.packetHandling.packets.response;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.packets.Packet;

public abstract class AbstractResponsePacket implements Packet {

  private static final Logger log = LoggerFactory.getLogger(AbstractResponsePacket.class);
  
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
    log.debug(String.format("remaining: %s, position: %s",buffer.hasRemaining(), buffer.position()));
    
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
