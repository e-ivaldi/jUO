package com.foolver.juo.packetHandling.packets.request;

import com.foolver.juo.packetHandling.packets.Packet;

public class AbstractRequestPacket implements Packet {

  @Override
  public byte[] getBytes() {
    return new byte[0];
  }

  @Override
  public boolean hasBytes() {
    return false;
  }

}
