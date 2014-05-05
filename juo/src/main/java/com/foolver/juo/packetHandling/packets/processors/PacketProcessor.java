package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;

public interface PacketProcessor<P extends Packet> {

  Packet processPacket(PlayerInfo playerInfo, P packet);
  
}
