package com.foolver.juo.packetHandling.packets.response;

public class ConnectToGameServerPacket extends AbstractResponsePacket {

  @Override
  protected void fillBuffer() {  
    buffer.put((byte) 0x8c);
    buffer.putInt(127 << 24 | 0 << 16 | 0 << 8 | 1);
    buffer.putShort((short)2593);
    buffer.putInt(1 << 24 | 2 << 16 | 3 << 8 | 4);
  }

  @Override
  public int getBufferSize() {
    return 11;
  }

}
