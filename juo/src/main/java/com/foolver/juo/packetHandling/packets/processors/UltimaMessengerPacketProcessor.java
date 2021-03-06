package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.response.UltimaMessengerPacket;

public class UltimaMessengerPacketProcessor implements PacketProcessor<UltimaMessengerPacket> {

  @Override
  public Packet processPacket(PlayerInfo playerInfo, UltimaMessengerPacket packet) {
    return new UltimaMessengerPacket(packet.getId1(), packet.getId2());
  }

}
