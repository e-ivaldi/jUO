package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.GraphicalEffectPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

public class GraphicalEffectPacketProcessor implements PacketProcessor<GraphicalEffectPacket> {

  @Override
  public Packet processPacket(GraphicalEffectPacket packet) {
    return new EmptyPacket();
  }

}
