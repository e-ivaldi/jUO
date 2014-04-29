package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.response.DrawGamePlayerPacket;
import com.foolver.juo.packetHandling.packets.shared.CharacterMoveACKPacket;
import com.foolver.juo.packetHandling.packets.utils.Direction;

public class ResyncRequestProcessor implements PacketProcessor<CharacterMoveACKPacket> {

  @Override
  public Packet processPacket(CharacterMoveACKPacket packet) {
    return new DrawGamePlayerPacket((short) 1496, (short) 1628, (byte) 10, Direction.EAST);
  }

}
