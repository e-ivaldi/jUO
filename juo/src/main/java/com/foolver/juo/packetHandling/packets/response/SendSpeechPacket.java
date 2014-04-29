package com.foolver.juo.packetHandling.packets.response;

import com.foolver.juo.util.ByteUtil;

public class SendSpeechPacket extends AbstractResponsePacket {

  private byte type;
  private String text;

  public SendSpeechPacket(byte type, String text) {
    super(false);
    this.type = type;
    this.text = text;
    allocateAndSetupBuffer();
  }

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x1c);
    buffer.putShort((byte) getBufferSize());
    buffer.putInt(24 << 0xFF | 16 << 0xFF | 8 << 0xFF | 0XFF);
    buffer.putShort((short) (8 << 0xFF | 0XFF)); // model
    buffer.put(type);
    buffer.putShort((byte) 1); // color
    buffer.putShort((byte) 1); // font
    buffer.put(ByteUtil.getPaddedBytesOfString("admin", 30));
    buffer.put(ByteUtil.getPaddedBytesOfString(text, text.length()));
  }

  @Override
  public int getBufferSize() {
    return 44 + text.length() + 1;
  }

}
