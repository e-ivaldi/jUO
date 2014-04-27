package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

public class EmptyPacketProcessor implements PacketProcessor<EmptyPacket> {

  @Override
  public Packet processPacket(EmptyPacket packet) {
    return new EmptyPacket();
  }

}
