package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.MoveRequestPacket;
import com.foolver.juo.packetHandling.packets.response.CharacterMoveACKPacket;

public class MoveRequestPacketProcessor implements PacketProcessor<MoveRequestPacket> {

  @Override
  public Packet processPacket(MoveRequestPacket packet) {
    // return new MultiPacket(
    // new UpdatePlayerPacket((short) 100, (short) 100, (byte) 0x10,
    // packet.getDirection() ),
    // new MovePlayerPacket(packet.getDirection()),
    // new DrawGamePlayerPacket((short) 100, (short) 100, (byte) 0x10,
    // packet.getDirection()) );
    return new CharacterMoveACKPacket(packet.getSequenceNumber());
  }
}
