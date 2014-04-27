package com.foolver.juo.packetHandling.packets.processors;

import java.nio.ByteBuffer;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.GameServerLoginPacket;
import com.foolver.juo.packetHandling.packets.response.DebugPacket;
import com.foolver.juo.packetHandling.packets.special.MultiPacket;

public class GameServerLoginPacketProcessor implements PacketProcessor<GameServerLoginPacket> {

  @Override
  public Packet processPacket(GameServerLoginPacket packet) {
    // Packet p1 = new CharacterListPacket();
    // Packet p2 = new LoginCompletePacket();
    // Packet p3 = new ServersListPacket();
    // Packet response = new MultiPacket(new EmptyPacket());

    ByteBuffer buffer1 = ByteBuffer.allocate(19);
    buffer1.put((byte) 0x20); // draw game player
    buffer1.putInt(0); // creatureId
    buffer1.putShort((short) 0); // bodyType
    buffer1.put((byte) 0x00); // unknown
    buffer1.putShort((short) 0); // skinColor
    buffer1.put((byte) 0x00); // flagbyte
    buffer1.putShort((short) 500); // xLoc
    buffer1.putShort((short) 500); // yLoc
    buffer1.putShort((short) 0); // unknown
    buffer1.put((byte) 0x00); // direction
    buffer1.put((byte) 0x00); // zloc

    ByteBuffer buffer2 = ByteBuffer.allocate(1);
    buffer2.put((byte) 0x55); // draw game player

    return new MultiPacket(new DebugPacket(buffer1));

  }
}
