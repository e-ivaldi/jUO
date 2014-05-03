package com.foolver.juo.packetHandling.packets.response;


public class AllSkillsPacket extends AbstractResponsePacket {
  
  //  Skill Lock State
  //  0x0: Up
  //  0x1: Down
  //  0x2: Locked
    
  //  Type
  //  0x00: Full List of skills
  //  0xFF: Single skill update
  //  0x02: Full List of skills with skill cap for each skill
  //  0xDF: Single skill update with skill cap for skill
  
  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x3A); // packetId
    buffer.putShort((short) getBufferSize());
    buffer.put((byte)0x00); // 0x02

    for(short i=1;i<52;i++){
      buffer.putShort(i); // skill id 1 based
      buffer.putShort((short)500); // skill value * 10
      buffer.putShort((short)500); // raw value * 10
      buffer.put((byte)0x00); // skill lock      
    }
    
    buffer.putShort((short)0);
  }

  @Override
  public int getBufferSize() {
    return 6 + 51 * 7;
  }

}
