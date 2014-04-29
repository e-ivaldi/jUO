package com.foolver.juo.packetHandling.packets.shared;

import com.foolver.juo.packetHandling.packets.response.AbstractResponsePacket;

public class ClientVersionPacket extends AbstractResponsePacket {

  private String version;

  public ClientVersionPacket(String version) {
    super(false);
    this.version = version;
    allocateAndSetupBuffer();
  }

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0xBD);
    buffer.putShort((short) 3);
  }

  @Override
  public int getBufferSize() {
    return 9;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

}
