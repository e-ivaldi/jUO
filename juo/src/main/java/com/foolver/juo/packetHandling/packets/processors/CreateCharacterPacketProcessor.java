package com.foolver.juo.packetHandling.packets.processors;

import com.foolver.juo.game.PlayerInfo;
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
  public Packet processPacket(PlayerInfo playerInfo, CreateCharacterPacket packet) {
    //TODO validate input data and use the data from the input packet
    playerInfo.getSkill(packet.getSkill1()).setRawValue((short) (packet.getSkill1Value()*10));
    playerInfo.getSkill(packet.getSkill2()).setRawValue((short) (packet.getSkill2Value()*10));
    playerInfo.getSkill(packet.getSkill3()).setRawValue((short) (packet.getSkill3Value()*10));
    playerInfo.setName(packet.getName());
    playerInfo.setSkinColor(packet.getSkinColor());
    
    //TODO: set to this player info that it's in game now
    
    return new MultiPacket(        
        new CharLocaleAndBodyDebugPacket(playerInfo),
        //TODO: do I need to send a DrawGamePlayer juyst after a CharocaleAndBodyDebug ?
        new DrawGamePlayerPacket(playerInfo),
        new LoginCompletePacket(),
        new StatusBarInfoPacket(playerInfo),
        new EnableLockedClientFeaturesPacket());
  }
}
