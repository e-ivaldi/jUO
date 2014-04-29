package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.SpeechRequestPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;
import com.foolver.juo.packetHandling.packets.response.SendSpeechPacket;

public class SpeechRequestPacketProcessor implements PacketProcessor<SpeechRequestPacket> {

  @Override
  public Packet processPacket(SpeechRequestPacket packet) {
    if (packet.getText().startsWith("send")) {
      return new SendSpeechPacket((byte) 0x01, "Welcome to JUO!");
    }
    return new EmptyPacket();
  }

}
