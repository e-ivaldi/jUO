package com.foolver.juo.packetHandling.packets.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.SpeechRequestPacket;
import com.foolver.juo.packetHandling.packets.response.CharLocaleAndBodyDebugPacket;
import com.foolver.juo.packetHandling.packets.response.DrawGamePlayerPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;
import com.foolver.juo.packetHandling.packets.response.MovePlayerPacket;
import com.foolver.juo.packetHandling.packets.response.SendSpeechPacket;
import com.foolver.juo.packetHandling.packets.response.UpdatePlayerPacket;
import com.foolver.juo.packetHandling.packets.shared.CharacterMoveACKPacket;
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
    } else if (packet.getText().startsWith("5")) {
      return new CharLocaleAndBodyDebugPacket();
    } else if (packet.getText().startsWith("move")) {
      int direction = new Integer(packet.getText().split(" ")[1]);
      return new MovePlayerPacket(Direction.fromValue((byte) direction));
    } else if (packet.getText().startsWith("speech")) {
      return new SendSpeechPacket((byte) 0x07, "this is an admin message");
    } else if (packet.getText().startsWith("send")) {
      return new SendSpeechPacket((byte) 0x07, "this is an admin message");
    }
    return new EmptyPacket();
    // return new
    // DebugPacket(DatatypeConverter.parseHexBinary(packet.getText()));
  }

}
