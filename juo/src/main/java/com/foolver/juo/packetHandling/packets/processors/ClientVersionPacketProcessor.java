package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.response.ConnectToGameServerPacket;
import com.foolver.juo.packetHandling.packets.response.LoginCompletePacket;
import com.foolver.juo.packetHandling.packets.response.StatusBarInfoPacket;
import com.foolver.juo.packetHandling.packets.shared.ClientVersionPacket;
import com.foolver.juo.packetHandling.packets.special.MultiPacket;

public class ClientVersionPacketProcessor implements PacketProcessor<ClientVersionPacket> {

  @Override
  public Packet processPacket(PlayerInfo playerInfo, ClientVersionPacket packet) {
    return new MultiPacket(
        new ClientVersionPacket(packet.getVersion()),
        new LoginCompletePacket(),
        new StatusBarInfoPacket(playerInfo),
        new ConnectToGameServerPacket());
  }
}
