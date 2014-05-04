package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.shared.WarModePacket;

public class WarModePacketProcessor implements PacketProcessor<WarModePacket> {

  @Override
  public Packet processPacket(WarModePacket packet) {
    //TODO implement the logic here
    return new WarModePacket(packet.isWarMode());
  }

}
