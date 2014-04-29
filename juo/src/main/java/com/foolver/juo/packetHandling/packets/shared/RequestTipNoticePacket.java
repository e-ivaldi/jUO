package com.foolver.juo.packetHandling.packets.shared;

import com.foolver.juo.packetHandling.packets.response.AbstractResponsePacket;

public class RequestTipNoticePacket extends AbstractResponsePacket {

  private short lastTip;
  private byte flag;

  public RequestTipNoticePacket(short lastTip, byte flag) {
    super(false);
    this.lastTip = lastTip;
    this.flag = flag;
    allocateAndSetupBuffer();
  }

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0xa7);
    buffer.putShort(lastTip);
    buffer.put(flag);

  }

  @Override
  public int getBufferSize() {
    return 4;
  }

  public short getLastTip() {
    return lastTip;
  }

  public byte getFlag() {
    return flag;
  }

}
