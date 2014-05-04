package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.SpeechRequestPacket;
import com.foolver.juo.packetHandling.packets.response.DrawGamePlayerPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

public class SpeechRequestPacketProcessor implements PacketProcessor<SpeechRequestPacket> {

  @Override
  public Packet processPacket(SpeechRequestPacket packet) {
    if (packet.getText().startsWith(".send")) {
      // return new SendSpeechPacket((byte) 0x01, "Welcome to JUO!");
      PlayerInfo playerInfo = PlayerInfo.getInstance();
      String cmds[] = packet.getText().split(" ");
      if (cmds.length == 4) {
        short posX = Short.parseShort(cmds[1]);
        short posY = Short.parseShort(cmds[2]);
        byte posZ = Byte.parseByte(cmds[3]);
        playerInfo.setPosX(posX);
        playerInfo.setPosY(posY);
        playerInfo.setPosZ(posZ);
        //return new UpdatePlayerPacket(playerInfo);
        return new DrawGamePlayerPacket(playerInfo);
      }
    }
    return new EmptyPacket();
  }
}
