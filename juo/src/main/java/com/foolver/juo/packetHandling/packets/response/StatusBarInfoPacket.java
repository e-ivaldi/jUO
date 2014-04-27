package com.foolver.juo.packetHandling.packets.response;

import com.foolver.juo.util.ByteUtil;

public class StatusBarInfoPacket extends AbstractResponsePacket {

  @Override
  protected void fillBuffer() {  
    buffer.put((byte) 0x11);
    buffer.putShort((short)getBufferSize());
    buffer.putInt(0); //player serial
    buffer.put(ByteUtil.getPaddedBytesOfString("playername", 30));
    buffer.putShort((short)255); // current hit points
    buffer.putShort((short)255); // max hit points
    buffer.put((byte) 0x00); // name change flag
    buffer.put((byte) 0x01); // status flag
    buffer.put((byte) 0x00); // sex + race
    buffer.putShort((short)255); // str
    buffer.putShort((short)255); // dex
    buffer.putShort((short)255); // int
    buffer.putShort((short)255); // current stamina
    buffer.putShort((short)255); // max stamina
    buffer.putShort((short)255); // current mana
    buffer.putShort((short)255); // max mana
    buffer.putInt(5000); // gold in pack
    buffer.putShort((short)100); // armor rating
    buffer.putShort((short)30); // weight
    
    /*
    Notes
    For characters other than the player, Current Hitpoints and Max Hitpoints are not the actual values. Max Hitpoints is a fixed value, and Current Hitpoints works like a percentage. This is to assist in blocking injection style tools from seeing real hitpoint values.

    Name Change Flag
    1: Allowed to Change In StatusBar (like with pets)
    0: Not allowed

    Status Flag
    0x00: no more data following (end of packet here).
    0x01: T2A Extended Info
    0x03: UOR Extended Info
    0x04: AOS Extended Info (4.0+)
    0x05: UOML Extended Info (5.0+)
    0x06: UOKR Extended Info (UOKR+)

    Sex + Race
    0: Male Human
    1: Female Human
    2: Male Elf
    3: Female Elf

    Armor Rating
    Armor Rating depends on client settings. If client has AOS Resistances enabled, this should be the Physical Resist instead of older AR Rating.

    UOML+ Race Flag
    1: Human
    2: Elf
    3: Gargoyle

    Resistances
    Resistances can be negatives. Easiest method for handling this correctly is, if a negative is to be sent is: 0x10000+amount
    */
   
  }

  @Override
  public int getBufferSize() {
    return 66;
  }

}
