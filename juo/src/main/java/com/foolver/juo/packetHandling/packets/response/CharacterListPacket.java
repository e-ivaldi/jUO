package com.foolver.juo.packetHandling.packets.response;

import com.foolver.juo.util.ByteUtil;

public class CharacterListPacket extends AbstractResponsePacket {

  private final int CHARS_NUMBER = 5;
  private final int CHAR_INFO_SIZE = 60;

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0xa9); // packetId
    buffer.putShort((short) getBufferSize()); // length
    buffer.put((byte) 0x05); // numChars
    buffer.put(ByteUtil.getPaddedBytesOfString("name1", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("password", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("name2", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("password", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("name3", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("password", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("name4", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("password", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("name5", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("password", 30)); // name acc1
    buffer.put((byte) 0x01); // number of starting locations
    buffer.put((byte) 0x01); // locations index
    buffer.put(ByteUtil.getPaddedBytesOfString("Trinsic", 31)); // city name
    buffer.put(ByteUtil.getPaddedBytesOfString("Town", 31)); // area of city or town
    buffer.putInt(0);
  }

  @Override
  public int getBufferSize() {
    return 5 + CHARS_NUMBER * CHAR_INFO_SIZE + 67;
  }

}
