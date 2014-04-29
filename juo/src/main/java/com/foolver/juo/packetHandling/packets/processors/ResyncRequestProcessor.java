package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.response.DrawGamePlayerPacket;
import com.foolver.juo.packetHandling.packets.response.UpdatePlayerPacket;
import com.foolver.juo.packetHandling.packets.shared.CharacterMoveACKPacket;
import com.foolver.juo.packetHandling.packets.utils.Direction;

public class ResyncRequestProcessor implements PacketProcessor<CharacterMoveACKPacket> {

  @Override
  public Packet processPacket(CharacterMoveACKPacket packet) {
    // todo set a better packet here?
    // return new CharacterMoveACKPacket(packet.getMovementSequenceKey(),
    // packet.getNotorietyFlag());
    //return new UpdatePlayerPacket((short) 2000, (short) 2000, (byte) 0, Direction.EAST);
    return new DrawGamePlayerPacket((short) 1496, (short) 1628, (byte) 10, Direction.EAST);
  }

}
