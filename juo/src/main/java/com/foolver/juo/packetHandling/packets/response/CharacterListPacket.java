package com.foolver.juo.packetHandling.packets.response;

import java.util.ArrayList;
import java.util.List;

import com.foolver.juo.util.ByteUtil;

public class CharacterListPacket extends AbstractResponsePacket {

  private final int CHARS_NUMBER = 5;
  private final int CHAR_INFO_SIZE = 60;
  private final int CITY_INFO_SIZE = 65;
  
  //TODO put this in a proper class
  private final static List<String> cities = new ArrayList<String>(){{
    add("Britain");
    add("Trinsic");
    add("Moonglow");
    add("Minoc");
  }};

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0xa9); // packetId
    buffer.putShort((short) getBufferSize()); // length
    buffer.put((byte) 0x05); // numChars
    buffer.put(ByteUtil.getPaddedBytesOfString("", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("", 30)); // name acc1
    buffer.put(ByteUtil.getPaddedBytesOfString("", 30)); // name acc1
    buffer.put((byte) 0x04); // number of starting locations
    
    byte cityOffset = 0x00;
    for(int i = 0;i<cities.size();i++){
      String cityName = cities.get(i);
      buffer.put(cityOffset);
      buffer.put(ByteUtil.getPaddedBytesOfString(cityName, 31));
      buffer.put((byte)0x00);
      buffer.put(ByteUtil.getPaddedBytesOfString(cityName + " Tavern", 31));
      buffer.put((byte)0x00);   
      cityOffset+=0x01;
    }
    
    
    
    
    //buffer.put(ByteUtil.getPaddedBytesOfString("Trinsic", 31)); // city name
    //buffer.put(ByteUtil.getPaddedBytesOfString("Town", 31)); // area of city or town
    buffer.putInt(1);
  }

  @Override
  public int getBufferSize() {
    return 9 + (CHARS_NUMBER * CHAR_INFO_SIZE) + (cities.size() * CITY_INFO_SIZE);
  }

}
