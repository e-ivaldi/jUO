package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.DeleteCharacterPacket;
import com.foolver.juo.packetHandling.packets.response.ResendCharactersAfterDeletePacket;

public class DeleteCharacterPacketProcessor implements PacketProcessor<DeleteCharacterPacket> {

  @Override
  public Packet processPacket(PlayerInfo playerInfo, DeleteCharacterPacket packet) {
    return new ResendCharactersAfterDeletePacket();
  }

}
