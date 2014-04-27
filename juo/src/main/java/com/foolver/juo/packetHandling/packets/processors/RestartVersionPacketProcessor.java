package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.RestartVersionPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

public class RestartVersionPacketProcessor implements PacketProcessor<RestartVersionPacket> {

  @Override
  public Packet processPacket(RestartVersionPacket packet) {
    return new EmptyPacket();
  }

}
