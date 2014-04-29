package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.GameServerLoginPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

public class GameServerLoginPacketProcessor implements PacketProcessor<GameServerLoginPacket> {

  @Override
  public Packet processPacket(GameServerLoginPacket packet) {
    //return new SendSpeechPacket((byte) 0x01, "Welcome to JUO!");
    return new EmptyPacket();
  }
}
