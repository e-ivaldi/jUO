package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.request.ServerListRemoveEntryPacket;
import com.foolver.juo.packetHandling.packets.response.LoginCompletePacket;

public class ServerListRemoveEntryPacketProcessor implements PacketProcessor<ServerListRemoveEntryPacket> {

  @Override
  public LoginCompletePacket processPacket(PlayerInfo playerInfo, ServerListRemoveEntryPacket packet) {
    return new LoginCompletePacket();
  }

}
