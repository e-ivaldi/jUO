package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.response.DrawGamePlayerPacket;
import com.foolver.juo.packetHandling.packets.shared.CharacterMoveACKPacket;

public class ResyncRequestPacketProcessor implements PacketProcessor<CharacterMoveACKPacket> {

  @Override
  public Packet processPacket(PlayerInfo playerInfo, CharacterMoveACKPacket packet) {
    return new DrawGamePlayerPacket(playerInfo);
  }

}
