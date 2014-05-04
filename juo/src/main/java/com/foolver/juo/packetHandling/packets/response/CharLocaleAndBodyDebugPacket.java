package com.foolver.juo.packetHandling.packets.response;

import com.foolver.juo.game.PlayerInfo;

public class CharLocaleAndBodyDebugPacket extends AbstractResponsePacket {

  private PlayerInfo playerInfo;

  public CharLocaleAndBodyDebugPacket(PlayerInfo playerInfo) {
    super(false);
    this.playerInfo = playerInfo;
    this.allocateAndSetupBuffer();
  }

  @Override
  public void fillBuffer() {
    buffer.put((byte) 0x1B); // draw game player
    buffer.putInt(playerInfo.getSerialId()); // serial
    buffer.putInt(0); // unknown
    buffer.putShort((short) 400); // bodyType
    buffer.putShort(playerInfo.getPosX()); // xLoc
    buffer.putShort(playerInfo.getPosY()); // yLoc
    buffer.put(playerInfo.getPosZ()); // zloc
    buffer.put(playerInfo.getDir().getValue()); // direction
    buffer.put((byte) 0x00); // unknown
    buffer.put((byte) 0xFF);
    buffer.put((byte) 0xFF);
    buffer.put((byte) 0xFF);
    buffer.put((byte) 0xFF);
    buffer.putShort((short) 0);
    buffer.putShort((short) 0);
    buffer.putShort((short) 6144); // map width
    buffer.putShort((short) 4096); // map height
    buffer.putShort((short) 0); // unknown
    buffer.putInt(0); // unknown
  }

  @Override
  public int getBufferSize() {
    return 37;
  }
  
}
