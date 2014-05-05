package com.foolver.juo.packetHandling.packets.response;

import com.foolver.juo.game.PlayerInfo;

public class DrawNakedMob extends AbstractResponsePacket {

  private PlayerInfo playerInfo;

  public DrawNakedMob(PlayerInfo playerInfo) {
    super(false);
    this.playerInfo = playerInfo;
    allocateAndSetupBuffer();
  }

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x77); // packetId
    buffer.putInt(playerInfo.getInternalSerialId());
    buffer.putShort((short)400);
    buffer.putShort(playerInfo.getPosX());
    buffer.putShort(playerInfo.getPosY());
    buffer.put(playerInfo.getPosZ());
    buffer.put(playerInfo.getDir().getValue());
    buffer.putShort(playerInfo.getSkinColor());
    buffer.put((byte)0x00); // status flag
    buffer.put((byte)0x1); // notoriety
    //TODO finish this stuff
    
  }

  @Override
  public int getBufferSize() {
    return 17;
  }

}
