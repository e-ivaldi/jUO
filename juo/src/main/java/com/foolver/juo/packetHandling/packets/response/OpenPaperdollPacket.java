package com.foolver.juo.packetHandling.packets.response;

import com.foolver.juo.util.ByteUtil;

public class OpenPaperdollPacket extends AbstractResponsePacket {

  private int serialId;
  private byte flag;
  private String text;

  public OpenPaperdollPacket(int serialId, byte flag, String text) {
    super(false);
    this.serialId = serialId;
    this.flag = flag;
    this.text = text;
    allocateAndSetupBuffer();
  }

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x88); // packetId
    buffer.putInt(serialId);
    buffer.put(ByteUtil.getPaddedBytesOfString(text, 60));
    buffer.put(flag);
  }

  @Override
  public int getBufferSize() {
    return 66;
  }

}
