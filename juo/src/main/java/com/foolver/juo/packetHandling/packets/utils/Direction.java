package com.foolver.juo.packetHandling.packets.utils;

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
    for (Direction d : values()) {
      if (d.getValue() == b) {
        return d;
      }
    }
    return null;
  }
}
