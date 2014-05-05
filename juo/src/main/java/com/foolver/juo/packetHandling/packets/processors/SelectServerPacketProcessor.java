package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.SelectServerPacket;
import com.foolver.juo.packetHandling.packets.response.CharacterListPacket;

public class SelectServerPacketProcessor implements PacketProcessor<SelectServerPacket> {

  @Override
  public Packet processPacket(PlayerInfo playerInfo, SelectServerPacket packet) {
    return new CharacterListPacket();
  }

}
