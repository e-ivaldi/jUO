package com.foolver.juo.packetHandling.packets.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.DoubleClickPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;
import com.foolver.juo.packetHandling.packets.response.OpenPaperdollPacket;

public class DoubleClickPacketProcessor implements PacketProcessor<DoubleClickPacket> {

  private final static Logger log = LoggerFactory.getLogger(DoubleClickPacketProcessor.class);

  @Override
  public Packet processPacket(DoubleClickPacket packet) {
    PlayerInfo playerInfo = PlayerInfo.getInstance();
    Packet response;
    if (packet.getObjectId() == 0) {
      log.info("user clicked on itself (0), sending the paperdoll info..?");
      response = new OpenPaperdollPacket(playerInfo.getSerialId(), (byte) 0x00, "The coolest player in the world");
    } else if (packet.getObjectId() == 0x80000000) {
      log.info("user clicked on itself (0x80000000), sending the paperdoll info..?");
      response = new OpenPaperdollPacket(playerInfo.getSerialId(), (byte) 0x00, "The coolest player in the world");
    } else {
      response = new EmptyPacket();
    }
    return response;
  }
}
