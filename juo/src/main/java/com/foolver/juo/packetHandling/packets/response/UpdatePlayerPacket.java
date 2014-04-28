package com.foolver.juo.packetHandling.packets.response;

import com.foolver.juo.packetHandling.packets.utils.Direction;

public class UpdatePlayerPacket extends AbstractResponsePacket {

  private short xLoc;
  private short yLoc;
  private byte zLoc;
  Direction dir;
  
  public UpdatePlayerPacket(short xLoc, short yLoc, byte zLoc, Direction dir){
    super(false);
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    this.zLoc = zLoc;
    this.dir = dir;   
    allocateAndSetupBuffer();
  }
 
  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x77); // packetId
    buffer.putInt(0); // playerId
    buffer.putShort((short)0); // model
    buffer.putShort(xLoc); // xloc
    buffer.putShort(yLoc); // yloc
    buffer.put(zLoc); // zloc
    if(dir == null){
      dir = Direction.NORTH_WEST;
    }
    buffer.put(dir.getValue()); // direction
    buffer.putShort((short)0); // hue/skin color
    buffer.put((byte) 0x00); // status flag
    buffer.put((byte) 0x00); // highlight color    
  }

  @Override
  public int getBufferSize() {
    return 17;
  }

}
