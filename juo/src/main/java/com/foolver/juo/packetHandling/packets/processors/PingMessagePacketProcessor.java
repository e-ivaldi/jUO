package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.shared.PingMessagePacket;

public class PingMessagePacketProcessor implements PacketProcessor<PingMessagePacket> {

  @Override
  public Packet processPacket(PlayerInfo playerInfo, PingMessagePacket packet) {
    return new PingMessagePacket(packet.getSequence());
  }

}
