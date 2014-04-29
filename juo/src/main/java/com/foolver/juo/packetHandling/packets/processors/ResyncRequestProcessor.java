package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.response.DrawGamePlayerPacket;
import com.foolver.juo.packetHandling.packets.shared.CharacterMoveACKPacket;

public class ResyncRequestProcessor implements PacketProcessor<CharacterMoveACKPacket> {

  @Override
  public Packet processPacket(CharacterMoveACKPacket packet) {
    PlayerInfo playerInfo = PlayerInfo.getInstance();
    return new DrawGamePlayerPacket(       
        playerInfo.getPosX(), 
        playerInfo.getPosY(),
        playerInfo.getPosZ(),
        playerInfo.getDir());
  }

}
