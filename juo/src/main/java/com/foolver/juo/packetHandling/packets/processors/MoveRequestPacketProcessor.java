package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.MoveRequestPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

public class MoveRequestPacketProcessor implements PacketProcessor<MoveRequestPacket> {

  @Override
  public Packet processPacket(MoveRequestPacket packet) {
    return new EmptyPacket();
  }
}
