package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.LoginCharacterPacket;
import com.foolver.juo.packetHandling.packets.response.CharLocaleAndBodyDebugPacket;
import com.foolver.juo.packetHandling.packets.response.ConnectToGameServerPacket;
import com.foolver.juo.packetHandling.packets.response.EnableLockedClientFeaturesPacket;
import com.foolver.juo.packetHandling.packets.response.LoginCompletePacket;
import com.foolver.juo.packetHandling.packets.response.StatusBarInfoPacket;
import com.foolver.juo.packetHandling.packets.special.MultiPacket;

public class LoginCharacterPacketProcessor implements PacketProcessor<LoginCharacterPacket> {

  @Override
  public Packet processPacket(LoginCharacterPacket packet) {
    return new MultiPacket(
        new CharLocaleAndBodyDebugPacket(),
        new LoginCompletePacket(),
        new ConnectToGameServerPacket(),
        new StatusBarInfoPacket(),
        new EnableLockedClientFeaturesPacket());
  }
}
