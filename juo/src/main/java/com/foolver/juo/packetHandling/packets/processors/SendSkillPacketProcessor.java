package com.foolver.juo.packetHandling.packets.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.SendSkillPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;
import com.foolver.juo.packetHandling.packets.utils.Skill;

public class SendSkillPacketProcessor implements PacketProcessor<SendSkillPacket> {

  private static final Logger log = LoggerFactory.getLogger(SendSkillPacketProcessor.class);

  @Override
  public Packet processPacket(PlayerInfo playerInfo, SendSkillPacket packet) {
    log.info(String.format("user wants to modify the skill (id: %s), this needs to be implements",
        Skill.fromValue(packet.getSkillId())));
    return new EmptyPacket();
  }

}
