package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.SpeechRequestPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

public class SpeechRequestPacketProcessor implements PacketProcessor<SpeechRequestPacket> {

  @Override
  public Packet processPacket(SpeechRequestPacket packet) {
    return new EmptyPacket();
  }

}
