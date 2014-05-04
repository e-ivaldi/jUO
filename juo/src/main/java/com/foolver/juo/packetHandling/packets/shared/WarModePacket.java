package com.foolver.juo.packetHandling.packets.shared;

import com.foolver.juo.packetHandling.packets.response.AbstractResponsePacket;

public class WarModePacket extends AbstractResponsePacket {

  private boolean warMode;

  public WarModePacket(boolean warMode) {
    super(false);
    this.warMode = warMode;
    allocateAndSetupBuffer();
  }

  public boolean isWarMode() {
    return warMode;
  }

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x72); // packetId
    buffer.put((byte) (warMode ? 0x01 : 0x00));
    buffer.put((byte) 0x00);
    buffer.put((byte) 0x32);
    buffer.put((byte) 0x00);
  }

  @Override
  public int getBufferSize() {
    return 5;
  }

}
