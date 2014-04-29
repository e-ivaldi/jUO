package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.response.ConnectToGameServerPacket;
import com.foolver.juo.packetHandling.packets.response.DrawGamePlayerPacket;
import com.foolver.juo.packetHandling.packets.response.EnableLockedClientFeaturesPacket;
import com.foolver.juo.packetHandling.packets.response.LoginCompletePacket;
import com.foolver.juo.packetHandling.packets.response.StatusBarInfoPacket;
import com.foolver.juo.packetHandling.packets.shared.ClientVersionPacket;
import com.foolver.juo.packetHandling.packets.shared.PingMessagePacket;
import com.foolver.juo.packetHandling.packets.special.MultiPacket;
import com.foolver.juo.packetHandling.packets.utils.Direction;

public class ClientVersionPacketProcessor implements PacketProcessor<ClientVersionPacket> {

  @Override
  public Packet processPacket(ClientVersionPacket packet) {
    return new MultiPacket(
        new ClientVersionPacket(packet.getVersion()),
        new LoginCompletePacket(),
        new ConnectToGameServerPacket(),
        new StatusBarInfoPacket(),
        new EnableLockedClientFeaturesPacket());
  }
}
