package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.SingleClickPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

public class SingleClickPacketProcessor implements PacketProcessor<SingleClickPacket> {

  @Override
  public Packet processPacket(SingleClickPacket packet) {
    return new EmptyPacket();
  }
}
