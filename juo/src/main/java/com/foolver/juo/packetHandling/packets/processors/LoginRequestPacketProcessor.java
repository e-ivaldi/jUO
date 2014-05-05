package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.LoginRequestPacket;
import com.foolver.juo.packetHandling.packets.response.ServersListPacket;
import com.foolver.juo.packetHandling.packets.response.WrongCredentialsPacket;

public class LoginRequestPacketProcessor implements PacketProcessor<LoginRequestPacket> {

  @Override
  public Packet processPacket(PlayerInfo playerInfo, LoginRequestPacket packet) {
    // TODO implement the real logic here
    Packet responsePacket;
    if (packet.getUsername().equals("admin") && packet.getPassword().equals("admin")) {
      responsePacket = new ServersListPacket();
    } else {
      responsePacket = new WrongCredentialsPacket();
    }

    return responsePacket;
  }

}
