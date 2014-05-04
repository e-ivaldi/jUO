package com.foolver.juo.packetHandling.packets.response;

import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.game.SkillModel;
import com.foolver.juo.packetHandling.packets.utils.Skill;


public class AllSkillsPacket extends AbstractResponsePacket {
  
  private PlayerInfo playerInfo;
  
  public AllSkillsPacket(PlayerInfo playerInfo) {
    super(false);
    this.playerInfo = playerInfo;
    allocateAndSetupBuffer();
  }
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

    for(Skill skill : Skill.values()){
      SkillModel skillModel = playerInfo.getSkill(skill);
      buffer.putShort(skill.get1BasedValue()); // skill id 1 based
      buffer.putShort(skillModel.getRawValue()); // skill value * 10
      buffer.putShort(skillModel.getRawValue()); // raw value * 10
      buffer.put(skillModel.getLockState().getValue()); // skill lock      
    }
    
    buffer.putShort((short)0);
  }

  @Override
  public int getBufferSize() {
    return 6 + Skill.values().length * 7;
  }

}
