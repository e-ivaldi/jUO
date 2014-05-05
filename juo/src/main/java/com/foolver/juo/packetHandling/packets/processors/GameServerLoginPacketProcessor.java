package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.GameServerLoginPacket;
import com.foolver.juo.packetHandling.packets.response.SendSpeechPacket;

public class GameServerLoginPacketProcessor implements PacketProcessor<GameServerLoginPacket> {

  @Override
  public Packet processPacket(PlayerInfo playerInfo, GameServerLoginPacket packet) {
    return new SendSpeechPacket((byte) 0x01, "Welcome to JUO!");
    //return new EmptyPacket();
  }
}
