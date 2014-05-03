package com.foolver.juo.packetHandling.packets.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.RequestPlayerStatusPacket;
import com.foolver.juo.packetHandling.packets.response.AllSkillsPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;
import com.foolver.juo.packetHandling.packets.response.StatusBarInfoPacket;
import com.foolver.juo.packetHandling.packets.response.UpdateMobileStatusPacket;

public class RequestPlayerStatusPacketProcessor implements PacketProcessor<RequestPlayerStatusPacket> {

  private static final Logger log = LoggerFactory.getLogger(RequestPlayerStatusPacketProcessor.class);

  private static final byte BASIC_STATUS = 0x04;
  private static final byte REQUEST_SKILL = 0x05;

  private static boolean alreadySent = false;

  @Override
  public Packet processPacket(RequestPlayerStatusPacket packet) {
    log.info(String.format("Request player status of type: %s", packet.getStatus()));
    return prepareResponse(packet);
  }

  private Packet prepareResponse(RequestPlayerStatusPacket packet) {
    Packet response;
    PlayerInfo playerInfo = PlayerInfo.getInstance();
    if (packet.getStatus() == BASIC_STATUS) {
      response = new StatusBarInfoPacket(playerInfo.getSerialId());
    } else if (packet.getStatus() == REQUEST_SKILL) {
      // this is not good at all, need to understand why the first time 0x34 is requested
      // the client blocks if it gets the skills packet instead of the update mobile status ...
      if (!alreadySent) {
         response = new UpdateMobileStatusPacket(playerInfo.getSerialId(), packet.getStatus(), -1);
        alreadySent = true;
      } else {
        response = new AllSkillsPacket();
      }
    } else {
      log.error("client is requesting a non recnognized status for the player");
      response = new EmptyPacket();
    }
    return response;
  }
}
