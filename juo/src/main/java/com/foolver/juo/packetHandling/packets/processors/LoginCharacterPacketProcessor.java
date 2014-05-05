package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.LoginCharacterPacket;
import com.foolver.juo.packetHandling.packets.response.CharLocaleAndBodyDebugPacket;
import com.foolver.juo.packetHandling.packets.response.DrawGamePlayerPacket;
import com.foolver.juo.packetHandling.packets.response.EnableLockedClientFeaturesPacket;
import com.foolver.juo.packetHandling.packets.response.LoginCompletePacket;
import com.foolver.juo.packetHandling.packets.response.StatusBarInfoPacket;
import com.foolver.juo.packetHandling.packets.special.MultiPacket;

public class LoginCharacterPacketProcessor implements PacketProcessor<LoginCharacterPacket> {

  @Override
  public Packet processPacket(PlayerInfo playerInfo, LoginCharacterPacket packet) {
    return new MultiPacket(        
        new CharLocaleAndBodyDebugPacket(playerInfo),
        //TODO: do I need to send a DrawGamePlayer juyst after a CharocaleAndBodyDebug ?
        new DrawGamePlayerPacket(playerInfo),
        new LoginCompletePacket(),
        new StatusBarInfoPacket(playerInfo),
        new EnableLockedClientFeaturesPacket());
  }
}
