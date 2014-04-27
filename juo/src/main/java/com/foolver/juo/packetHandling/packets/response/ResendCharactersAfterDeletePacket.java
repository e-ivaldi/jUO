package com.foolver.juo.packetHandling.packets.response;

import com.foolver.juo.util.ByteUtil;

public class ResendCharactersAfterDeletePacket extends AbstractResponsePacket {

  private final int CHARS_NUMBER = 5;
  private final int CHAR_INFO_SIZE = 60;

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x86); // packetId
    buffer.putShort((short) getBufferSize()); // length
    buffer.put((byte) 0x05); // numChars
    buffer.put(ByteUtil.getPaddedBytesOfString("name1ad", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("password", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("name2ad", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("password", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("name3ad", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("password", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("name4ad", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("password", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("name5ad", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("password", 30)); // name acc1
  }

  @Override
  public int getBufferSize() {
    return 4 + CHARS_NUMBER * CHAR_INFO_SIZE;
  }

}
