package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.CreateCharacterPacket;
import com.foolver.juo.packetHandling.packets.response.CharLocaleAndBodyDebugPacket;
import com.foolver.juo.packetHandling.packets.response.DrawGamePlayerPacket;
import com.foolver.juo.packetHandling.packets.response.EnableLockedClientFeaturesPacket;
import com.foolver.juo.packetHandling.packets.response.LoginCompletePacket;
import com.foolver.juo.packetHandling.packets.response.StatusBarInfoPacket;
import com.foolver.juo.packetHandling.packets.special.MultiPacket;

public class CreateCharacterPacketProcessor implements PacketProcessor<CreateCharacterPacket> {

  @Override
  public Packet processPacket(CreateCharacterPacket packet) {
    //TODO validate and use the data from the input packet
    PlayerInfo playerInfo = PlayerInfo.getInstance();
    return new MultiPacket(        
        new CharLocaleAndBodyDebugPacket(
            playerInfo.getSerialId(),
            playerInfo.getPosX(), 
            playerInfo.getPosY(),
            playerInfo.getPosZ(),
            playerInfo.getDir()),
            //TODO: do I need to send a DrawGamePlayer juyst after a CharocaleAndBodyDebug ?
        new DrawGamePlayerPacket(
            playerInfo.getSerialId(),
            playerInfo.getPosX(), 
            playerInfo.getPosY(),
            playerInfo.getPosZ(),
            playerInfo.getDir()),
        new LoginCompletePacket(),
        new StatusBarInfoPacket(playerInfo.getSerialId()),
        new EnableLockedClientFeaturesPacket());
  }
}
