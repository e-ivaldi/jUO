package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.RestartVersionPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

public class RestartVersionPacketProcessor implements PacketProcessor<RestartVersionPacket> {

  @Override
  public Packet processPacket(PlayerInfo playerInfo, RestartVersionPacket packet) {
    return new EmptyPacket();
  }

}
