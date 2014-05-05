package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.shared.RequestTipNoticePacket;

public class RequestTipNoticePacketProcessor implements PacketProcessor<RequestTipNoticePacket> {

  @Override
  public Packet processPacket(PlayerInfo playerInfo, RequestTipNoticePacket packet) {
    return new RequestTipNoticePacket(packet.getLastTip(), packet.getFlag());
  }

}
