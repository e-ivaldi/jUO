package com.foolver.juo.packetHandling.packets.response;

import com.foolver.juo.game.PlayerInfo;

public class DrawGamePlayerPacket extends AbstractResponsePacket {

  private PlayerInfo playerInfo;

  public DrawGamePlayerPacket(PlayerInfo playerInfo) {
    super(false);
    this.playerInfo = playerInfo;
    allocateAndSetupBuffer();
  }

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x20); // packetId
    buffer.putInt(playerInfo.getSerialId()); // playerId
    buffer.putShort((short) 400); // model - bodytype
    buffer.put((byte)0x00); // unknown
    buffer.putShort(playerInfo.getSkinColor()); // skin color / hue
    buffer.put((byte)0x01); // flag byte
    buffer.putShort(playerInfo.getPosX()); // xloc
    buffer.putShort(playerInfo.getPosY()); // yloc
    buffer.putShort((short) 0); // unknown
    buffer.put(playerInfo.getDir().getValue()); // direction
    buffer.put(playerInfo.getPosZ()); // zloc
  }

  @Override
  public int getBufferSize() {
    return 19;
  }

}
