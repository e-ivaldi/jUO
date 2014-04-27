package com.foolver.juo.packetHandling.packets.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.SpeechRequestPacket;
import com.foolver.juo.packetHandling.packets.response.CharacterMoveACKPacket;
import com.foolver.juo.packetHandling.packets.response.DrawGamePlayerPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;
import com.foolver.juo.packetHandling.packets.response.MovePlayerPacket;
import com.foolver.juo.packetHandling.packets.response.UpdatePlayerPacket;
import com.foolver.juo.packetHandling.packets.utils.Direction;

public class SpeechRequestPacketProcessor implements PacketProcessor<SpeechRequestPacket> {

  private static final Logger log = LoggerFactory.getLogger(SpeechRequestPacketProcessor.class);

  @Override
  public Packet processPacket(SpeechRequestPacket packet) {
    if (packet.getText().equals("1")) {
      return new DrawGamePlayerPacket((short) 1000, (short) 1000, (byte) 0x00, Direction.EAST);
    } else if (packet.getText().equals("2")) {
      return new MovePlayerPacket(Direction.SOUTH);
    } else if (packet.getText().equals("3")) {
      return new UpdatePlayerPacket((short) 1000, (short) 1000, (byte) 0x00, Direction.EAST);
    } else if (packet.getText().startsWith("4")) {
      int fakeSequenceKey = new Integer(packet.getText().split(" ")[1]);
      log.info(String.format("fake seq key: %s", fakeSequenceKey));
      return new CharacterMoveACKPacket((byte) fakeSequenceKey);
    }
    return new EmptyPacket();
  }

}
