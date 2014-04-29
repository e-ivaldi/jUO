package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.RequestPlayerStatusPacket;
import com.foolver.juo.packetHandling.packets.response.UpdateMobileStatusPacket;

public class RequestPlayerStatusPacketProcessor implements PacketProcessor<RequestPlayerStatusPacket> {

  @Override
  public Packet processPacket(RequestPlayerStatusPacket packet) {
    return new UpdateMobileStatusPacket(packet.getPlayerSerial(), packet.getStatus(), -1);
  }
}
