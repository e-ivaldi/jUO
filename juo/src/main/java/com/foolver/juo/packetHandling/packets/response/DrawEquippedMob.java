package com.foolver.juo.packetHandling.packets.response;

import com.foolver.juo.game.PlayerInfo;

public class DrawEquippedMob extends AbstractResponsePacket {

  private PlayerInfo playerInfo;

  public DrawEquippedMob(PlayerInfo playerInfo) {
    super(false);
    this.playerInfo = playerInfo;
    allocateAndSetupBuffer();
  }

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x78); // packetId
    buffer.putShort((short)getBufferSize());
    buffer.putInt(playerInfo.getInternalSerialId());
    buffer.putShort((short)400); // body?
    buffer.putShort(playerInfo.getPosX());
    buffer.putShort(playerInfo.getPosY());
    buffer.put(playerInfo.getPosZ());
    buffer.put(playerInfo.getDir().getValue());
    buffer.putShort(playerInfo.getSkinColor());
    buffer.put((byte)0x00); // status flag
    buffer.put((byte)0x1); // notoriety
    
    // loop of the items
    // for the moment no items
    
    buffer.putInt(0); // end of the item loop
    
    
  }

  @Override
  public int getBufferSize() {
    return 23;
  }

}
