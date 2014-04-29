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
    buffer.putInt(0);
    buffer.put((byte) 0xff); // model
    buffer.put((byte) 0xff); // model
    buffer.put(type);
    buffer.putShort((byte) 0); // color
    buffer.putShort((byte) 0); // font
    buffer.put(ByteUtil.getPaddedBytesOfString("admin", 30));
    text = prepareText(text);
    buffer.put(ByteUtil.getPaddedBytesOfString(text, text.length()));
  }

  private String prepareText(String text2) {
    return text += '\0';
  }

  @Override
  public int getBufferSize() {
    return 44 + text.length() + 1;
  }

}
