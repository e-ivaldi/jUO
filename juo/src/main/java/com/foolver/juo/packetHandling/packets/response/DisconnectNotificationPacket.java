package com.foolver.juo.packetHandling.packets.response;

public class DisconnectNotificationPacket extends AbstractResponsePacket {

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x01); // packetId
    buffer.put((byte) 0xFF);
    buffer.put((byte) 0xFF);
    buffer.put((byte) 0xFF);
    buffer.put((byte) 0xFF); 
  }

  @Override
  public int getBufferSize() {
    return 5;
  }

}
