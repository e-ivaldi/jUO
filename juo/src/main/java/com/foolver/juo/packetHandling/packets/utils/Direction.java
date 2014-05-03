package com.foolver.juo.packetHandling.packets.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.util.ByteUtil;

public enum Direction {
  
  NORTH((byte) 0x00), 
  NORTH_EAST((byte) 0x01),
  EAST((byte) 0x02),
  SOUTH_EAST((byte) 0x03),
  SOUTH((byte) 0x04),
  SOUTH_WEST((byte) 0x05),
  WEST((byte) 0x06),
  NORTH_WEST((byte) 0x07);
  
  Byte value;

  Direction(Byte value) {
    this.value = value;
  }

  public byte getValue() {
    return this.value;
  }

  public static Direction fromValue(byte b) {
    //TODO: look at this, don't know why but sometimes the direction packet is + 80 bytes
    if(b < 0x00){
      b -= 0x80;
    }
    
    for (Direction d : values()) {
      if (d.getValue() == b) {
        return d;
      }
    }
    throw new IllegalArgumentException(String.format("unable to find the direction %s", ByteUtil.getPrintable(b)));
  }
}
